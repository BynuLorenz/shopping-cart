package com.gapstars.assessment.shoppingcart.controller.payload.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/** Class acts as request class for Customer related functions */
@Getter
@Setter
public class CustomerRequest extends BaseRequest {

  /** property related to customer first name */
  @NotBlank(message = "First Name is mandatory")
  @NotNull(message = "First Name is null")
  @JsonProperty("firstName")
  private String firstName ;

  /** property related to customer last name */
  @NotBlank(message = "Last Name is mandatory")
  @JsonProperty("lastName")
  private String lastName ;

}
