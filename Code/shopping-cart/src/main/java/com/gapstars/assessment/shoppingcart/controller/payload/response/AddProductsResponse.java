package com.gapstars.assessment.shoppingcart.controller.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/** Class acts as data transfer object for Customer Entity */
@Getter
@Setter
public class AddProductsResponse extends Response {

  /** util related to Customer Id */
  @JsonProperty("customerId")
  private Long customerId ;

  /** util related to Cart Id */
  @JsonProperty("cartId")
  private Long cartId ;

}
