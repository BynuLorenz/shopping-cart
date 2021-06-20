package com.gapstars.assessment.shoppingcart.controller.helper;

import com.gapstars.assessment.shoppingcart.common.dto.CustomerDto;
import com.gapstars.assessment.shoppingcart.common.dto.ProductDto;
import com.gapstars.assessment.shoppingcart.common.enums.ResponseCode;
import com.gapstars.assessment.shoppingcart.controller.payload.request.AddProductsRequest;
import com.gapstars.assessment.shoppingcart.controller.payload.request.CustomerRequest;
import com.gapstars.assessment.shoppingcart.controller.payload.request.ProductRequest;
import com.gapstars.assessment.shoppingcart.controller.payload.response.AddProductsResponse;
import com.gapstars.assessment.shoppingcart.controller.payload.response.CartAmountResponse;
import com.gapstars.assessment.shoppingcart.controller.payload.response.CustomerResponse;
import com.gapstars.assessment.shoppingcart.controller.payload.response.ProductResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Class acts as data transfer object for Customer Entity */
@Slf4j
@Component
public class ControllerHelper {

  @Autowired( required = false )
  ModelMapper modelMapper;

  /**
   * Generate Customer Dto from Customer Request
   * @param request Customer Request
   * @return Customer Dto
   */
  public CustomerDto toDto ( CustomerRequest request ) {

    CustomerDto customerDto = modelMapper.map( request, CustomerDto.class );
    return customerDto;
  }

  /**
   * Get Product Dto from Add Product request
   * @param request
   * @return
   */
  public List<ProductDto> toDtos ( AddProductsRequest request ) {

    List<ProductDto> dtoList = request.getProductIds().stream().map( id -> {
      ProductDto dto = new ProductDto();
      dto.setProductId( id );
      return dto;
    }).collect(Collectors.toList());
    return dtoList;
  }

  /**
   * Set Customer Response Status
   * @param response Customer Response
   */
  public void setResponseStatus ( CustomerResponse response ) {

    response.setResponseCode( ResponseCode.SUCCESS.getCode() );
    response.setResponseMsg( "Success" );
  }

  /**
   * Set Cart Amount Response Status
   * @param response Cart Amount Response
   */
  public void setResponseStatus ( CartAmountResponse response ) {

    response.setResponseCode( ResponseCode.SUCCESS.getCode() );
    response.setResponseMsg("Success");
  }

  /**
   * Set Add Products Response Status
   * @param response Add Product Response
   */
  public void setResponseStatus ( AddProductsResponse response ) {

    response.setResponseCode( ResponseCode.SUCCESS.getCode() );
    response.setResponseMsg("Success");
  }

  /**
   * Get Product Dto by Product Request
   * @param request Product Request Class
   * @return ProductDto Dto class
   */
  public ProductDto toDto ( ProductRequest request ) {

    ProductDto dto = modelMapper.map( request, ProductDto.class );
    dto.setProductId( null );
    return dto;
  }

  /**
   * Set Product Response Status
   * @param response Product Response
   */
  public void setResponseStatus ( ProductResponse response ) {

    response.setResponseCode( ResponseCode.SUCCESS.getCode() );
    response.setResponseMsg("Success");
  }

}
