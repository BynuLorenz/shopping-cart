package com.gapstars.assessment.shoppingcart.controller.payload.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/** Response class for Customer related returns */
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class CustomerResponse extends Response {

  /** property related to customer first name */
  @JsonProperty("firstName")
  private String firstName ;

  /** property related to customer last name */
  @JsonProperty("lastName")
  private String lastName ;

  /** property related to customer id in the system */
  @JsonProperty("id")
  private long id;

}
