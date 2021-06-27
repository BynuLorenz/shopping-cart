package com.gapstars.assessment.shoppingcart.controller.payload.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/** Response class for Customer related returns */
@Getter
@Setter
public class CustomerResponse extends Response {

  /** Related to customer first name */
  @JsonProperty("firstName")
  private String firstName ;

  /** Related to customer last name */
  @JsonProperty("lastName")
  private String lastName ;

  /** Related to customer last name */
  @JsonProperty("cartId")
  private Long cartId ;

  /** util related to customer id in the system */
  @JsonProperty("id")
  private long id;

}
