package com.gapstars.assessment.shoppingcart.common.dto;


import com.gapstars.assessment.shoppingcart.common.enums.ProductName;
import com.gapstars.assessment.shoppingcart.common.enums.ProductTitle;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/** Class acts as data transfer object for Product Entity */
public class ProductDto extends BaseDto {

  /** property related to product title */
  private ProductTitle productTitle;

  /** property related to product name */
  private ProductName productName;

  /** property related to product price */
  private BigDecimal price;

  /** property related to product tax */
  private BigDecimal tax;

}
