package com.gapstars.assessment.shoppingcart.controller;


import com.gapstars.assessment.shoppingcart.common.dto.CustomerDto;
import com.gapstars.assessment.shoppingcart.common.dto.ProductDto;
import com.gapstars.assessment.shoppingcart.common.path.WsPath;
import com.gapstars.assessment.shoppingcart.controller.helper.ControllerHelper;
import com.gapstars.assessment.shoppingcart.controller.payload.request.AddProductsRequest;
import com.gapstars.assessment.shoppingcart.controller.payload.request.CustomerRequest;
import com.gapstars.assessment.shoppingcart.controller.payload.response.AddProductsResponse;
import com.gapstars.assessment.shoppingcart.controller.payload.response.CartAmountResponse;
import com.gapstars.assessment.shoppingcart.controller.payload.response.CustomerResponse;
import com.gapstars.assessment.shoppingcart.controller.payload.response.Response;
import com.gapstars.assessment.shoppingcart.service.CustomerService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Controller for Customer related functions */
@Slf4j
@RestController
@RequestMapping(WsPath.CUSTOMER)
public class CustomerController {

  @Autowired( required = false )
  CustomerService customerService ;
  @Autowired( required = false )
  ControllerHelper helper;


  /**
   * Handles Customer add function
   * @param request Request Class
   * @return CustomerResponse Response
   */
  @PostMapping
  public CustomerResponse createCustomer(@Valid @RequestBody CustomerRequest request ) {

    log.info( "Received createCustomer() request. Request : {}", request);
    CustomerDto dto = helper.toDto( request );
    CustomerResponse response = customerService.createCustomer( dto );
    helper.setResponseStatus( response );
    log.info( "Executed createCustomer() request. Response : {}", response);
    return response ;

  }

  /**
   * Get all Customers
   * @return List of Customer Response List
   */
  @GetMapping
  public List<CustomerResponse> getAllCustomers() {

    log.info( "Received getAllCustomers() request.");
    List<CustomerResponse> responses = customerService.getAllCustomers();
    log.info( "Executed getAllCustomers() request. Response : {}", responses);
    return responses;
  }

  /**
   * Handles addition of products to the Customer
   * @param request Request Class
   * @return CustomerResponse Response Class
   */
  @PostMapping(WsPath.PRODUCTS)
  public AddProductsResponse addProductsToCart( @Valid @RequestBody AddProductsRequest request ) {

    log.info( "Received addProductsToCart() request. Request : {}", request);
    List<ProductDto> dtoList = helper.toDtos( request );
    AddProductsResponse response = customerService.addProductsToCart( dtoList, request.getCustomerId() );
    helper.setResponseStatus( response );
    log.info( "Executed addProductsToCart() request. Response : {}", response);
    return response ;

  }

  /**
   * Update Cart amount of a specific Cart
   * @param cartId Cart Id
   * @return CartAmountResponse
   */
  @GetMapping(WsPath.CART)
  public CartAmountResponse getCartAmounts( @Valid @NotNull @RequestParam("cartId") Long cartId) {

    log.info( "Received updateCartAmounts() request. Id : {}", cartId);
    CartAmountResponse response = customerService.getCartAmounts( cartId );
    helper.setResponseStatus( response );
    log.info( "Executed updateCartAmounts() request. Response : {}", response);
    return response ;
  }

}
