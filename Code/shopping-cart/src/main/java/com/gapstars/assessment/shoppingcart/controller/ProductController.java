package com.gapstars.assessment.shoppingcart.controller;


import com.gapstars.assessment.shoppingcart.common.dto.ProductDto;
import com.gapstars.assessment.shoppingcart.common.path.WsPath;
import com.gapstars.assessment.shoppingcart.controller.helper.ControllerHelper;
import com.gapstars.assessment.shoppingcart.controller.payload.request.ProductRequest;
import com.gapstars.assessment.shoppingcart.controller.payload.response.ProductResponse;
import com.gapstars.assessment.shoppingcart.service.ProductService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Controller for Product related functions */
@Slf4j
@RestController
@RequestMapping(WsPath.PRODUCT)
public class ProductController {

  @Autowired( required = false )
  ProductService productService ;
  @Autowired( required = false )
  ControllerHelper helper;


  /**
   * Handles create product function
   * @param request Request Class
   * @return ProductResponse Response
   */
  @PostMapping
  public ProductResponse createProduct(@Valid @RequestBody ProductRequest request ) {

    log.info( "Received createProduct() request. Request : {}", request);
    ProductDto dto = helper.toDto( request );
    ProductResponse response = productService.createProduct( dto );
    helper.setResponseStatus( response );
    log.info( "Executed createCustomer() request. Response : {}", response);
    return response ;

  }

  /**
   * Get all Products
   * @return List of Product Responses
   */
  @GetMapping
  public List<ProductResponse> getAllProducts() {

    log.info( "Received getAllProducts() request");
    List<ProductResponse> responses = productService.getAllProducts();
    log.info( "Executed getAllProducts() request. Response : {}", responses);
    return responses;
  }



}
