package com.gapstars.assessment.shoppingcart.common.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/** Class acts as data transfer object for Customer Entity */
public class CustomerDto extends BaseDto {

  /** property related to customer first name */
  private String firstName ;

  /** property related to customer last name */
  private String lastName ;

}
