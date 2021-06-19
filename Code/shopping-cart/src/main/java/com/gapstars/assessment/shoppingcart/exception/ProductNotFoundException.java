
package com.gapstars.assessment.shoppingcart.exception;

/** Custom Exception class - Product Not FoundException */
public class ProductNotFoundException extends BaseException {

    public ProductNotFoundException(String errorMsg) {

        super(errorMsg);
    }
}
