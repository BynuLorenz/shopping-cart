package com.gapstars.assessment.shoppingcart.dao.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
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

  /** Property and relationship related to customer cart */
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "cart_id", nullable = false)
  private CartEntity cart;

  /** Property and relationship related to cart product */
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "product_id", nullable = false)
  private ProductEntity product;

  /** Property related to cart total amount */
  @Column(name="product_price")
  private BigDecimal price;

  /** Property related to cart total vat */
  @Column(name="product_vat")
  private BigDecimal vat;

  /** Property related to cart shipment fee */
  @Column(name="product_shipping_fee")
  private BigDecimal shippingFee;

}
