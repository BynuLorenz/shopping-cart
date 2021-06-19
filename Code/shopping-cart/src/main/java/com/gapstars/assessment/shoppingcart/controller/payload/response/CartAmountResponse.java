package com.gapstars.assessment.shoppingcart.controller.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/** Class acts as data transfer object for Customer Entity */
@Getter
@Setter
public class CartAmountResponse extends Response {

  /** property related to Total Cart Amount */
  @JsonProperty("totalCartAmount")
  private BigDecimal totalCartAmount ;

  /** property related to Total VAT Amount */
  @JsonProperty("totaVatAmount")
  private BigDecimal totaVatAmount ;

  /** property related to Total Shipping Amount */
  @JsonProperty("shippingAmount")
  private BigDecimal shippingAmount ;

  /** property related to Cart Id */
  @JsonProperty("cartId")
  private Long cartId ;

}
