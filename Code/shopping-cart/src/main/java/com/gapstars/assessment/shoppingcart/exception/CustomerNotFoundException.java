
package com.gapstars.assessment.shoppingcart.exception;

/** Custom Exception class - Customer NotFound Exception */
public class CustomerNotFoundException extends BaseException {

    public CustomerNotFoundException(String errorMsg) {

        super(errorMsg);
    }
}
