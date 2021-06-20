package com.gapstars.assessment.shoppingcart.common.dto;


import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/** Class acts as data transfer object for Product Entity */
public class ProductDto extends BaseDto {

  /** property related to product id */
  private Long productId;

  /** property related to product title */
  private Long productTitle;

  /** property related to product name */
  private String productName;

  /** property related to product price */
  private BigDecimal price;

  /** property related to product tax */
  private BigDecimal tax;

  /** property related to product quantity */
  private BigDecimal productQuantity;

}
