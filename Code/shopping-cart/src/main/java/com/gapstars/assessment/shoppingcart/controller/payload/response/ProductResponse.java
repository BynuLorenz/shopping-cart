package com.gapstars.assessment.shoppingcart.controller.payload.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/** Response class for Product related returns */
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ProductResponse extends Response {

  /** property related to product name */
  @JsonProperty("productName")
  private String productName;

  /** property related to product price */
  @JsonProperty("price")
  private BigDecimal price;

  /** property related to product tax */
  @JsonProperty("tax")
  private BigDecimal tax;

  /** property related to product quantity */
  @JsonProperty("productQuantity")
  private BigDecimal productQuantity;

  /** property related to product title id */
  @JsonProperty("productTitleId")
  private Long productTitleId;

  /** property related to product title */
  @JsonProperty("productTitle")
  private String productTitle;

  /** property related to product id */
  @JsonProperty("productId")
  private Long productId;


}
