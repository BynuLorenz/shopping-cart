
package com.gapstars.assessment.shoppingcart.exception;

import com.gapstars.assessment.shoppingcart.exception.BaseException;

/** Custom Exception class - Cart  Not Found Exception */
public class CartNotFoundException extends BaseException {

    public CartNotFoundException(String errorMsg) {

        super(errorMsg);
    }
}
