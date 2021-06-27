package com.gapstars.assessment.shoppingcart.service.helper;

import com.gapstars.assessment.shoppingcart.common.dto.CustomerDto;
import com.gapstars.assessment.shoppingcart.common.dto.ProductDto;
import com.gapstars.assessment.shoppingcart.common.util.ShoppingCartProperties;
import com.gapstars.assessment.shoppingcart.controller.payload.response.AddProductsResponse;
import com.gapstars.assessment.shoppingcart.controller.payload.response.CartAmountResponse;
import com.gapstars.assessment.shoppingcart.controller.payload.response.CustomerResponse;
import com.gapstars.assessment.shoppingcart.controller.payload.response.ProductResponse;
import com.gapstars.assessment.shoppingcart.dao.entity.CartEntity;
import com.gapstars.assessment.shoppingcart.dao.entity.CartProductEntity;
import com.gapstars.assessment.shoppingcart.dao.entity.CustomerEntity;
import com.gapstars.assessment.shoppingcart.dao.entity.ProductEntity;
import com.gapstars.assessment.shoppingcart.dao.entity.ProductTitleEntity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Helper class for Customer Service */
@Slf4j
@Component
public class ServiceHelper {

  @Autowired( required = false )
  ModelMapper modelMapper;
  @Autowired( required = false )
  ShoppingCartProperties properties;


  /**
   * Generate Customer Entity by Customer dto
   * @param customerDto Data Transfer Object
   * @return Customer Entity
   */
  public CustomerEntity toEntity ( CustomerDto customerDto ) {

    CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);
    customerEntity.setCreatedBy( customerDto.getFirstName() );
    customerEntity.setCreatedDateTime( LocalDateTime.now() );
    return customerEntity;
  }

  /**
   * Generate Cart Entity by Customer Entity
   * @param customerEntity Customer Entity
   * @return Cart Entity
   */
  public CartEntity toEntity ( CustomerEntity customerEntity ) {

    CartEntity cartEntity = new CartEntity();
    cartEntity.setCustomer( customerEntity );
    cartEntity.setCreatedBy( customerEntity.getFirstName() );
    cartEntity.setCreatedDateTime( LocalDateTime.now() );
    return cartEntity;
  }

  /**
   * Generate CartProduct Entity by Product Entity and Cart Entity
   * @param productEntity Product Entity
   * @param customerEntity Customer Entity
   * @return CartProductEntity
   */
  public CartProductEntity toEntity ( ProductEntity productEntity, CustomerEntity customerEntity) {

    CartProductEntity cartProductEntity = new CartProductEntity();
    cartProductEntity.setProduct( productEntity );
    cartProductEntity.setCart( customerEntity.getCartEntity() );
    cartProductEntity.setPrice( calculateCartProductAmount( productEntity ) );
    cartProductEntity.setVat( calculateProductVat( productEntity ) );
    cartProductEntity.setShippingFee( calculateProductShippingFee() );
    cartProductEntity.setCreatedDateTime( LocalDateTime.now() );
    cartProductEntity.setCreatedBy( customerEntity.getFirstName() );
    return cartProductEntity;
  }

  /**
   * Calculte total cart amount
   * @param cartEntity Cart Entity
   * @return Total Amount
   */
  public BigDecimal calculateCartTotalAmounts ( CartEntity cartEntity ) {

    return cartEntity.getCartProducts().stream().map( p -> p.getPrice() )
        .reduce( BigDecimal.ZERO, ( b1 ,  b2 ) -> b1.add( b2 ));
  }

  /**
   * Calculate total vat amount
   * Calculate Vat for each for entity - Price * Product Tax / 100
   * Then add all together
   * @param cartEntity Cart Entity
   * @return Total Vat Amount
   */
  public BigDecimal calculateTotalVat ( CartEntity cartEntity ) {

    return cartEntity.getCartProducts().stream().map( p -> p.getVat() )
        .reduce( BigDecimal.ZERO, ( b1 ,  b2 ) -> b1.add( b2 ));
  }

  /**
   * Set up updated Cart Entity
   * @param cartEntity Original Cart Entoty
   * @return CartEntity updated Cart Entity
   */
  public CartEntity updateEntity ( CartEntity cartEntity ) {

    cartEntity.setModifiedDateTime( LocalDateTime.now() );
    cartEntity.setModifiedBy( "SYSTEM" );
    return cartEntity;
  }

  /**
   * Generate Customer Response from Customer Entity
   * @param customerEntity Entity Class
   * @return Customer Response
   */
  public CustomerResponse toResponse ( CustomerEntity customerEntity ) {

    CustomerResponse customerResponse = new CustomerResponse();
    customerResponse.setFirstName( customerEntity.getFirstName() );
    customerResponse.setLastName( customerEntity.getLastName() );
    customerResponse.setId( customerEntity.getId() );
    return customerResponse;
  }

  /**
   * Generate Cart Amount Response from Cart Entity
   * @param cartId Cart Id
   * @param shippingAmount Total Shipping Amount
   * @param totalCartAmount Total Cart Amount
   * @param totalVal Total Vat Amount
   * @return Cart Amount Response
   */
  public CartAmountResponse toResponse ( Long cartId, BigDecimal shippingAmount, BigDecimal totalCartAmount, BigDecimal totalVal ) {

    CartAmountResponse cartAmountResponse = new CartAmountResponse();
    cartAmountResponse.setShippingAmount( shippingAmount );
    cartAmountResponse.setTotalCartAmount( totalCartAmount );
    cartAmountResponse.setTotaVatAmount( totalVal );
    cartAmountResponse.setCartId( cartId );
    cartAmountResponse.setTotalToBeSettled( shippingAmount.add( totalCartAmount ).add( totalVal ) );
    return cartAmountResponse;
  }

  /**
   * Generate Add Products Response from Entity Ids
   * @param cartId Cart Id
   * @param customerId Customer Id
   * @return
   */
  public AddProductsResponse toResponse ( Long customerId, Long cartId ) {

    AddProductsResponse response = new AddProductsResponse();
    response.setCustomerId( customerId );
    response.setCartId( cartId );
    return response;
  }

  /**
   * update product quantity after purchase
   * @param productEntity Entity class
   */
  public void updateProductQuantities ( ProductEntity productEntity ) {

    BigDecimal quantity = productEntity.getProductQuantity();
    productEntity.setProductQuantity( quantity.subtract(BigDecimal.ONE) );
    productEntity.setModifiedBy("SYSTEM");
    productEntity.setModifiedDateTime( LocalDateTime.now() );

  }

  /**
   * Generate Product Entity by Product dto
   * @param dto Product Dto
   * @return ProductEntity
   */
  public ProductEntity toEntity ( ProductDto dto, ProductTitleEntity titleEntity ) {

    ProductEntity entity = modelMapper.map( dto, ProductEntity.class );
    entity.setProductName(  entity.getProductName().toUpperCase() );
    entity.setProductTitle( titleEntity );
    entity.setCreatedBy( "SYSTEM" );
    entity.setCreatedDateTime( LocalDateTime.now() );
    return entity;
  }

  /**
   * Generate Product Response from Product Entity
   * @param productEntity Entity Class
   * @return ProductResponse Response Class
   */
  public ProductResponse toResponse ( ProductEntity productEntity ) {

    ProductResponse response = modelMapper.map( productEntity, ProductResponse.class );
    response.setProductTitle( productEntity.getProductTitle().getProductTitleName() );
    response.setProductTitleId( productEntity.getProductTitle().getId() );
    response.setProductId( productEntity.getId() );
    return response;
  }

  /**
   * Calculate the price of a specific product
   * @param entity Product Entity
   * @return Product Price
   */
  public BigDecimal calculateCartProductAmount ( ProductEntity entity ) {

    return entity.getPrice();
  }

  /**
   * Calculate VAT of a specific product.
   * Multiplies VAT percentage into Product Price
   * @param entity Product Entity
   * @return Product VAT
   */
  public BigDecimal calculateProductVat ( ProductEntity entity ) {

    BigDecimal productTax = entity.getPrice().multiply( entity.getTax() ).divide( BigDecimal.valueOf(100) );
    productTax.setScale(2, RoundingMode.HALF_EVEN);
    return productTax;
  }

  /**
   * Calculate product shipping fee
   * @return Shipping Fee
   */
  public BigDecimal calculateProductShippingFee () {

    return properties.getGenericShippingFeePerProduct();
  }

  /**
   * Calculate total shipping fee
   * Add all sipping fees into together
   * @param cartEntity Cart Entity
   * @return Total Shipping Fee
   */
  public BigDecimal calculateCartTotalShippingFee ( CartEntity cartEntity ) {

    return cartEntity.getCartProducts().stream().map( p -> p.getShippingFee() )
        .reduce( BigDecimal.ZERO, ( b1 ,  b2 ) -> b1.add( b2 ));
  }

  /**
   * Create get all customers response
   * @param entities Customer Entities
   * @return List of all Customers
   */
  public List<CustomerResponse> toResponse ( List<CustomerEntity> entities ) {

    List<CustomerResponse> responses = entities.stream().map( customer -> {

      CustomerResponse customerResponse = new CustomerResponse();
      customerResponse.setFirstName( customer.getFirstName() );
      customerResponse.setLastName( customer.getLastName() );
      customerResponse.setId( customer.getId() );
      // If only Customer has a Cart, fetch the Cart Id
      Optional<CartEntity> cartEntity = Optional.ofNullable( customer.getCartEntity() );
      if ( cartEntity.isPresent() ) customerResponse.setCartId( customer.getCartEntity().getId() );

      return customerResponse;
    } ).collect( Collectors.toList() );

    return responses;
  }


  /**
   * Create get all products list response
   * @param entities List of Product Entities
   * @return List of Product Response Entities
   */
  public List<ProductResponse> toListResponse ( List<ProductEntity> entities ) {

    List<ProductResponse> productResponses = entities.stream().map( product -> {

      ProductResponse response = modelMapper.map( product, ProductResponse.class );
      response.setProductTitle( product.getProductTitle().getProductTitleName() );
      response.setProductTitleId( product.getProductTitle().getId() );
      response.setProductId( product.getId() );
      return response;

    } ).collect( Collectors.toList() );
    return productResponses;
  }


}
