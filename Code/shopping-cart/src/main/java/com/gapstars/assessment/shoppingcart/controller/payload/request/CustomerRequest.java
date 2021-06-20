package com.gapstars.assessment.shoppingcart.controller.payload.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.Empty;

/** Class acts as request class for Customer related functions */
@Getter
@Setter
public class CustomerRequest extends BaseRequest {

  /** property related to customer first name */
  @NotEmpty( message = "First Name can not be empty" )
  @JsonProperty( "firstName" )
  private String firstName ;

  /** property related to customer last name */
  @NotEmpty( message = "Last Name can not be empty" )
  @JsonProperty( "lastName" )
  private String lastName ;

}
