package com.gapstars.assessment.shoppingcart.controller.payload.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/** Class acts as request class for Customer related functions */
@Getter
@Setter
public class ProductRequest extends BaseRequest {

  /** property related to customer product name */
  @NotEmpty( message = "Product Name can not be empty" )
  @JsonProperty( "productName" )
  private String productName ;

  /** property related to price */
  @NotNull(message = "Price can not be empty")
  @Min(1)
  @JsonProperty( "productPrice" )
  private BigDecimal price;

  /** property related to tax */
  @NotNull(message = "Tax can not be empty")
  @Min(value = 1, message = "Tax should be greater than 1.")
  @JsonProperty( "tax" )
  private BigDecimal tax;

  /** property related to product quantity */
  @NotNull(message = "Quantity can not be empty")
  @Min(value = 100, message = "Quantity should be greater than 100.")
  @JsonProperty( "productQuantity" )
  private BigDecimal productQuantity;

  /** property related to product title id */
  @NotNull(message = "Product Title Id can not be empty")
  @JsonProperty( "productTitleId" )
  private Long productTitleId;

}
