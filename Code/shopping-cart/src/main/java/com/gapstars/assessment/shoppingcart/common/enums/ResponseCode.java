package com.gapstars.assessment.shoppingcart.common.enums;

import lombok.Getter;

/** The enum class to represent Response Code */
@Getter
public enum ResponseCode {

  SUCCESS("00"),
  FAIL("01");

  private String code;

  ResponseCode(String code) {
    this.code = code;
  }

  public static ResponseCode fromString(String text) {
    for (ResponseCode b : ResponseCode.values()) {
      if (b.code.equalsIgnoreCase(text)) {
        return b;
      }
    }
    return null;
  }
}
