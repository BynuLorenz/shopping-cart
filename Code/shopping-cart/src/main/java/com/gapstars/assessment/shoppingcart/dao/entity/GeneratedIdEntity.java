// *************************************************************************************************
// PRODUCT : cargills-cash
// CLASS : GeneratedBaseEntity
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

package com.gapstars.assessment.shoppingcart.dao.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/** Entity super class for Generated Id */
@MappedSuperclass
@Getter
@Setter
public abstract class GeneratedIdEntity {

    /** property related to auto generated id in all entities */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
