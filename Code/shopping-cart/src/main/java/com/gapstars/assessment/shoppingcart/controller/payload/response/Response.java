package com.gapstars.assessment.shoppingcart.controller.payload.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/** Common response class specially for web requests */
@Getter
@Setter
public class Response {

  /** property related to response code */
  @JsonProperty("responseCode")
  private String responseCode ;

  /** property related to response msg */
  @JsonProperty("responseMsg")
  private String responseMsg;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

}
