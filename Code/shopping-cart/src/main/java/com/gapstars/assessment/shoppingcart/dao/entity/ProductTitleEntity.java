package com.gapstars.assessment.shoppingcart.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


/** Entity class for Product Title */
@Entity
@Getter
@Setter
@Table(name = "product_title")
public class ProductTitleEntity extends AuditableEntity {

  /** Property related to product price */
  @Column(name="product_title_name", unique = true)
  private String  productTitleName;

}
