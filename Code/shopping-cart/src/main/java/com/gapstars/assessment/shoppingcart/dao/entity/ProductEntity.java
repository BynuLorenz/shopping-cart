package com.gapstars.assessment.shoppingcart.dao.entity;

import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;


/** Entity class for Products */
@Entity
@Getter
@Setter
@Table(name = "product",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"product_name", "product_title"})})
public class ProductEntity extends AuditableEntity {

  /** Property related to product name */
  @Column(name="product_name")
  private String productName;

  /** Property related to product price */
  @Column(name="product_price")
  private BigDecimal price;

  /** Property related to product tax */
  @Column(name="product_tax")
  private BigDecimal tax;

  /** Property related to product quantity */
  @Column(name="product_quantity")
  private BigDecimal productQuantity;

  /** Relationship to Map Product Title */
  @ManyToOne(optional = false)
  @JoinColumn(name = "product_title")
  private ProductTitleEntity productTitle;

  /** Relationship to Map Cart Products */
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  private Set<CartProductEntity> cartProducts;

}
