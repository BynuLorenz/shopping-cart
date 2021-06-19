package com.gapstars.assessment.shoppingcart.service;


import com.gapstars.assessment.shoppingcart.common.dto.CustomerDto;
import com.gapstars.assessment.shoppingcart.common.dto.ProductDto;
import com.gapstars.assessment.shoppingcart.controller.payload.response.AddProductsResponse;
import com.gapstars.assessment.shoppingcart.controller.payload.response.CartAmountResponse;
import com.gapstars.assessment.shoppingcart.controller.payload.response.CustomerResponse;
import java.util.List;

/** Interface Customer Service */
public interface CustomerService {

  /**
   * Create Customer
   * @param customerDto Customer dto
   * @return CustomerResponse Response
   */
  CustomerResponse createCustomer( CustomerDto customerDto ) ;

  /**
   * Add new items to Cart
   * @param products list of products
   * @param customerId Customer Id
   * @return
   */
  AddProductsResponse addProductsToCart( List<ProductDto> products, Long customerId) ;

  /**
   * Calculate cart amounts like total amount, vat and Shipping amount
   * @param cartId Cart Id
   * @return
   */
  CartAmountResponse calculateCartAmounts ( Long cartId) ;

}
