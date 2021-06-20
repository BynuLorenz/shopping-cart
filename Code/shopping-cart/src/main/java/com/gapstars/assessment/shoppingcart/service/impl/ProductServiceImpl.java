package com.gapstars.assessment.shoppingcart.service.impl;


import com.gapstars.assessment.shoppingcart.common.dto.ProductDto;
import com.gapstars.assessment.shoppingcart.controller.payload.response.CustomerResponse;
import com.gapstars.assessment.shoppingcart.controller.payload.response.ProductResponse;
import com.gapstars.assessment.shoppingcart.dao.entity.CustomerEntity;
import com.gapstars.assessment.shoppingcart.dao.entity.ProductEntity;
import com.gapstars.assessment.shoppingcart.dao.entity.ProductTitleEntity;
import com.gapstars.assessment.shoppingcart.dao.repository.CartProductRepository;
import com.gapstars.assessment.shoppingcart.dao.repository.CartRepository;
import com.gapstars.assessment.shoppingcart.dao.repository.CustomerRepository;
import com.gapstars.assessment.shoppingcart.dao.repository.ProductRepository;
import com.gapstars.assessment.shoppingcart.dao.repository.ProductTitleRepository;
import com.gapstars.assessment.shoppingcart.exception.ProductNotFoundException;
import com.gapstars.assessment.shoppingcart.service.ProductService;
import com.gapstars.assessment.shoppingcart.service.helper.ServiceHelper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

  @Autowired( required = false )
  ProductTitleRepository productTitleRepository;
  @Autowired( required = false )
  ProductRepository productRepository;
  @Autowired( required = false )
  ServiceHelper helper;


  /**
   * Create Product
   * @param productDto Product dto
   * @return
   */
  @Override
  public ProductResponse createProduct(ProductDto productDto) {

    log.info( "createProduct() : dto received - {}", productDto );

    // Get Product Title Entity, if not found throw exception
    Optional<ProductTitleEntity> titleEntity = productTitleRepository.findById( productDto.getProductTitle() );
    titleEntity.orElseThrow(() -> new ProductNotFoundException( "Product not found. Id : " + productDto.getProductTitle() ));

    // Map dto to entity and save
    ProductEntity entity = helper.toEntity( productDto, titleEntity.get() );
    productRepository.save( entity );
    ProductResponse response = helper.toResponse( entity );
    log.info( "createProduct() : Method executed." );
    return response;
  }


  /**
   *
   * @return
   */
  @Override
  public List<ProductResponse> getAllProducts() {

    log.info( "getAllProducts() : dto received" );
    // Get all Products
    List<ProductEntity> allProducts = productRepository.findAll();

    // Convert to Response List
    List<ProductResponse> productDtos = allProducts.stream().map( product -> {
      ProductResponse response = helper.toResponse( product );
      return response;
    } ).collect( Collectors.toList() );

    log.info( "getAllProducts() : Method executed." );
    return productDtos;
  }
}
