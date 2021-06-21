

package com.gapstars.assessment.shoppingcart.common.path;

/** The interface will hold the request mapping path to */
public interface WsPath {

    /** WS Path for Customer controller */
    String CUSTOMER =  "/customer";

    /** WS Path for Product controller */
    String PRODUCT =  "/product";

    /** WS Path for add customer products */
    String ADD_PRODUCTS = "/add/products";

    /** WS Path for add customer products */
    String UPDATE_AMOUNTS = "/update/cart";
}
