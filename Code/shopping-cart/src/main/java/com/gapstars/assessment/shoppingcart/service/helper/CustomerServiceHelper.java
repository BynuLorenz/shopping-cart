package com.gapstars.assessment.shoppingcart.service.helper;

import com.gapstars.assessment.shoppingcart.common.dto.CustomerDto;
import com.gapstars.assessment.shoppingcart.controller.payload.response.AddProductsResponse;
import com.gapstars.assessment.shoppingcart.controller.payload.response.CartAmountResponse;
import com.gapstars.assessment.shoppingcart.controller.payload.response.CustomerResponse;
import com.gapstars.assessment.shoppingcart.dao.entity.CartEntity;
import com.gapstars.assessment.shoppingcart.dao.entity.CartProductEntity;
import com.gapstars.assessment.shoppingcart.dao.entity.CustomerEntity;
import com.gapstars.assessment.shoppingcart.dao.entity.ProductEntity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Helper class for Customer Service */
@Slf4j
@Component
public class CustomerServiceHelper {

  @Autowired( required = false )
  ModelMapper modelMapper;


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
    customerEntity.setCreatedBy( customerEntity.getFirstName() );
    customerEntity.setCreatedDateTime( LocalDateTime.now() );
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
    cartProductEntity.setCreatedDateTime( LocalDateTime.now() );
    cartProductEntity.setCreatedBy( customerEntity.getFirstName() );
    return cartProductEntity;
  }

  /**
   * Calculte total cart amount
   * @param cartEntity Cart Entity
   * @return Total Amount
   */
  public BigDecimal calculateCartTotalAmount ( CartEntity cartEntity ) {

    return cartEntity.getCartProducts().stream().map( p -> p.getProduct().getPrice() )
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

    return cartEntity.getCartProducts().stream().
        map( n -> ( n.getProduct().getPrice().multiply( n.getProduct().getTax() ).divide( BigDecimal.valueOf(100) ))
            .setScale(2, RoundingMode.HALF_EVEN )).reduce( BigDecimal.ZERO, ( b1, b2 ) -> b1.add( b2 ));
  }

  /**
   * Set up updated Cart Entity
   * @param cartEntity Original Cart Entoty
   * @param totalAmount Total Amount
   * @param totalVat Total Vat
   * @param totalShipping Total Shipping Amount
   * @return CartEntity updated Cart Entity
   */
  public CartEntity updateEntity ( CartEntity cartEntity, BigDecimal totalAmount, BigDecimal totalVat, BigDecimal totalShipping ) {

    cartEntity.setTotalAmount( totalAmount );
    cartEntity.setTotalVat( totalVat );
    cartEntity.setShipmentCost( totalShipping );
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
   * @param cartEntity Entity Class
   * @return Cart Amount Response
   */
  public CartAmountResponse toResponse ( CartEntity cartEntity ) {

    CartAmountResponse cartAmountResponse = new CartAmountResponse();
    cartAmountResponse.setShippingAmount( cartEntity.getShipmentCost() );
    cartAmountResponse.setTotalCartAmount( cartEntity.getTotalAmount() );
    cartAmountResponse.setTotaVatAmount( cartEntity.getTotalVat() );
    cartAmountResponse.setCartId( cartEntity.getId() );
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


}
