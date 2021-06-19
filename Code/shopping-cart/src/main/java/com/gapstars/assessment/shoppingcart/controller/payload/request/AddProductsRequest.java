package com.gapstars.assessment.shoppingcart.controller.payload.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/** Class acts as request class for add product function */
@Getter
@Setter
public class AddProductsRequest extends BaseRequest {

  /** property related to product name list */
  @JsonProperty("productNames")
  private List<String> productNames ;

  /** property related to customer id */
  @JsonProperty("customerId")
  private Long customerId ;

}
