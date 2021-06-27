package com.gapstars.assessment.shoppingcart.common.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/** Class acts as data transfer object for Customer Entity */
public class CustomerDto extends BaseDto {


  /** Default Constructor */
  public CustomerDto() {}

  /** Argument Constructor */
  public CustomerDto(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /** Property related to customer first name */
  private String firstName ;

  /** Property related to customer last name */
  private String lastName ;



}
