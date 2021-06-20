package com.gapstars.assessment.shoppingcart.controller.payload.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/** Class acts as request class for add product function */
@Getter
@Setter
public class AddProductsRequest extends BaseRequest {

  /** property related to product name list */
  @NotEmpty( message = "Product Names can not be empty" )
  @JsonProperty( "productIds" )
  private List<Long> productIds ;

  /** property related to customer id */
  @NotNull( message = "Customer Id can not be empty" )
  @JsonProperty( "customerId" )
  private Long customerId ;



}
