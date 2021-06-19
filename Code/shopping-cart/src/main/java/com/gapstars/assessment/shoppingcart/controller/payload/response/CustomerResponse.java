package com.gapstars.assessment.shoppingcart.controller.payload.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/** Class acts as data transfer object for Customer Entity */
@Getter
@Setter
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
