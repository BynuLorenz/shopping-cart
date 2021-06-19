package com.gapstars.assessment.shoppingcart.dao.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/** Entity class for Cart Product */
@Entity
@Getter
@Setter
@Table(name = "cart_products")
public class CartProductEntity extends AuditableEntity{

  /** property and relationship related to customer cart */
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "cart_id", nullable = false)
  private CartEntity cart;

  /** property and relationship related to cart product */
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "product_id", nullable = false)
  private ProductEntity product;

}
