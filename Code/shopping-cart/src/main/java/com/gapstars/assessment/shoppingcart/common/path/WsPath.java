// *************************************************************************************************
// PRODUCT : cargills-cash
// CLASS : WsPath
// ************************************************************************************************
//Copyright(C) 2020 Fortunaglobal (PRIVATE) LIMITED
/* All rights reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF
 * Fortunaglobal(PRIVATE) LIMITED.
 *
 * This copy of the Source Code is intended for Fortunaglobal (PRIVATE) LIMITED's
 * internal use only and is intended for view by persons duly authorized by the
 * management of Fortunaglobal (PRIVATE) LIMITED.
 * No part of this file may be reproduced or distributed in any form or by any
 * means without the written approval of the Management of
 * Fortunaglobal (PRIVATE) LIMITED.
 */
// *************************************************************************************************

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
