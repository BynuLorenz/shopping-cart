

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
