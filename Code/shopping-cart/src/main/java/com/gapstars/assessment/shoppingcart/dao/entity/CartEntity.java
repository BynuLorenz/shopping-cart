package com.gapstars.assessment.shoppingcart.dao.entity;


import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/** Entity class for Cart */
@Entity
@Getter
@Setter
@Table(name = "cart")
public class CartEntity extends AuditableEntity {

  /** property related to cart total amount */
  @Column(name="cart_total_amount")
  private BigDecimal totalAmount;

  /** property related to cart total vat */
  @Column(name="cart_total_vat")
  private BigDecimal totalVat;

  /** property related to cart shipment cost */
  @Column(name="cart_shipment_cost")
  private BigDecimal shipmentCost;

  /** property and relationship related to customer */
  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "customer_id", nullable = false)
  private CustomerEntity customer;

  /** Relationship to map Cart Cart */
  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<CartProductEntity> cartProducts;

}
