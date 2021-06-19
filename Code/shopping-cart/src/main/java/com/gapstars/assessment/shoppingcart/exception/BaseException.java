
package com.gapstars.assessment.shoppingcart.exception;

import lombok.Getter;

/** Parent Exception class of custom exception classes - Base Exception */
@Getter
public class BaseException extends RuntimeException {

    private String errorMsg;

    public BaseException(String errorMsg) {

        this.errorMsg = errorMsg;
    }
}
