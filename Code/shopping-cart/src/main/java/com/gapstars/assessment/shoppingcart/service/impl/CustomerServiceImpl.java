package com.gapstars.assessment.shoppingcart.service.impl;


import com.gapstars.assessment.shoppingcart.common.dto.CustomerDto;
import com.gapstars.assessment.shoppingcart.common.dto.ProductDto;
import com.gapstars.assessment.shoppingcart.controller.payload.response.AddProductsResponse;
import com.gapstars.assessment.shoppingcart.controller.payload.response.CartAmountResponse;
import com.gapstars.assessment.shoppingcart.controller.payload.response.CustomerResponse;
import com.gapstars.assessment.shoppingcart.dao.entity.CartEntity;
import com.gapstars.assessment.shoppingcart.dao.entity.CartProductEntity;
import com.gapstars.assessment.shoppingcart.dao.entity.CustomerEntity;
import com.gapstars.assessment.shoppingcart.dao.entity.ProductEntity;
import com.gapstars.assessment.shoppingcart.dao.repository.CartProductRepository;
import com.gapstars.assessment.shoppingcart.dao.repository.CartRepository;
import com.gapstars.assessment.shoppingcart.dao.repository.CustomerRepository;
import com.gapstars.assessment.shoppingcart.dao.repository.ProductRepository;
import com.gapstars.assessment.shoppingcart.exception.CartNotFoundException;
import com.gapstars.assessment.shoppingcart.exception.CustomerNotFoundException;
import com.gapstars.assessment.shoppingcart.exception.ProductNotFoundException;
import com.gapstars.assessment.shoppingcart.service.CustomerService;
import com.gapstars.assessment.shoppingcart.service.helper.CustomerServiceHelper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired( required = false )
  CustomerRepository customerRepository;
  @Autowired( required = false )
  ProductRepository productRepository;
  @Autowired( required = false )
  CartRepository cartRepository;
  @Autowired( required = false )
  CartProductRepository cartProductRepository;
  @Autowired( required = false )
  CustomerServiceHelper helper;


  /**
   * Create new Customer
   * @param customerDto Customer dto
   * @return
   */
  @Transactional( propagation = Propagation.REQUIRES_NEW )
  @Override
  public CustomerResponse createCustomer( CustomerDto customerDto ) {

    log.info( "createCustomer() : dto received - {}", customerDto );
    // Map dto to entity and save
    CustomerEntity customerEntity = helper.toEntity( customerDto );
    customerRepository.save( customerEntity );
    CustomerResponse response = helper.toResponse( customerEntity );
    log.info( "createCustomer() : Method executed." );
    return response;
  }


  /**
   * Add new products to cart
   * @param products list of products
   * @param customerId Customer Id
   * @return
   */
  @Transactional( propagation = Propagation.REQUIRES_NEW )
  @Override
  public AddProductsResponse addProductsToCart( List<ProductDto> products, Long customerId ) {

    log.info( "addItemsToCart() : Items to be added to cart - {}" , products );
    log.info( "addItemsToCart() : Customer id - {}" , customerId );
    // Check for Customer, else throw exception
    Optional< CustomerEntity > customerEntity = customerRepository.findById( customerId );
    log.info( "Is Customer exists ? {} ", customerEntity.isPresent());
    customerEntity.orElseThrow(() -> new CustomerNotFoundException( "Customer not found"+ customerId ));

    // Check for Customer Cart is available, else Create new cart.
    Optional<CartEntity> cartEntity = Optional.ofNullable( customerEntity.get().getCartEntity() );
    if ( cartEntity.isEmpty() ) {
      log.info( "addItemsToCart() : no cart available for Customer and creating. - {}", customerEntity.get().getFirstName() );

      CartEntity newCartEntity = helper.toEntity( customerEntity.get() );
      cartRepository.save( newCartEntity );
      customerEntity.get().setCartEntity( newCartEntity );
    }

    // Check products list to be added, else throw exception
    if ( products.size() == 0 ) {
      log.info( "addItemsToCart() : No products to be added. Exception is about to throw." );
      throw new ProductNotFoundException ( "No products to add." );
    }

    // Create Cart Product list
    Set<CartProductEntity> cartProductEntities = products.stream().map( product -> {

      // Check product is available, else throw exception
      Optional<ProductEntity> productEntity = productRepository.findByProductName( product.getProductName() );
      log.info("Is product exists ? {}", productEntity.isPresent());
      productEntity.orElseThrow(() -> new ProductNotFoundException("No such product"));

      CartProductEntity cartProductEntity = helper.toEntity( productEntity.get(), customerEntity.get() );
      return cartProductEntity;

    } ).collect( Collectors.toSet() );
    customerEntity.get().getCartEntity().setCartProducts( cartProductEntities );
    customerRepository.save( customerEntity.get() );

    // setting up response
    AddProductsResponse response = helper.toResponse( customerEntity.get().getId(), customerEntity.get().getCartEntity().getId() );
    log.info( "addItemsToCart() : Method executed." );
    return response;

  }


  /**
   * Calculate Cart Amounts
   * @param cartId Cart Id
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @Override
  public CartAmountResponse calculateCartAmounts(Long cartId) {

    log.info( "calculateCartAmounts() : Cart Id - {}", cartId );
    // Check Customer else throw exception
    Optional< CartEntity > cartEntity = cartRepository.findById( cartId );
    cartEntity.orElseThrow(() -> new CartNotFoundException( "Cart not found" ));

    // Calculate total amount
    BigDecimal totalAmount = helper.calculateCartTotalAmount( cartEntity.get() ) ;
    log.info( "calculateCartAmounts() : Total Amount {}", totalAmount );
    // Calculate total vat
    BigDecimal totalVat = helper.calculateTotalVat( cartEntity.get() );
    log.info( "calculateCartAmounts() : Total Vat {}", totalVat );

    // update entity
    helper.updateEntity( cartEntity.get(), totalAmount, totalVat, new BigDecimal("100") );
    cartRepository.save( cartEntity.get() );

    // setting up response
    CartAmountResponse response = helper.toResponse( cartEntity.get() );
    log.info( "calculateCartAmounts() : Method executed." );
    return response;
  }
}
