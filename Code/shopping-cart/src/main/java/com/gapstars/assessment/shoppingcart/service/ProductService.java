package com.gapstars.assessment.shoppingcart.service;


import com.gapstars.assessment.shoppingcart.common.dto.ProductDto;
import com.gapstars.assessment.shoppingcart.controller.payload.response.ProductResponse;
import java.util.List;

/** Interface Product Service */
public interface ProductService {

  /**
   * Create Product
   * @param productDto Product dto
   * @return ProductResponse Response
   */
  ProductResponse createProduct( ProductDto productDto ) ;

  /**
   * Get all products
   * @return List of Product Dtos
   */
  List<ProductResponse> getAllProducts ();

}
