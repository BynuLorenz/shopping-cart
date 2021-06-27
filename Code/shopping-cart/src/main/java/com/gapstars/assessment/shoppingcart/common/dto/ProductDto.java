package com.gapstars.assessment.shoppingcart.common.dto;


import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/** Class acts as data transfer object for Product Entity */
public class ProductDto extends BaseDto {

  /** Default Constructor */
  public ProductDto() {
  }

  /** Argument Constructor */
  public ProductDto(Long productId) {
    this.productId = productId;
  }

  /** Property related to product id */
  private Long productId;

  /** Property related to product title */
  private Long productTitle;

  /** Property related to product name */
  private String productName;

  /** Property related to product price */
  private BigDecimal price;

  /** Property related to product tax */
  private BigDecimal tax;

  /** Property related to product quantity */
  private BigDecimal productQuantity;

}
