package com.gapstars.assessment.shoppingcart.dao.entity;

import com.gapstars.assessment.shoppingcart.common.enums.ProductName;
import com.gapstars.assessment.shoppingcart.common.enums.ProductTitle;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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

  /** property related to product title */
  @Column(name="product_title")
  @Enumerated(EnumType.STRING)
  private ProductTitle productTitle;

  /** property related to product name */
  @Column(name="product_name")
  @Enumerated(EnumType.STRING)
  private ProductName productName;

  /** property related to product price */
  @Column(name="product_price")
  private BigDecimal price;

  /** property related to product tax */
  @Column(name="product_tax")
  private BigDecimal tax;

  /** Relationship to Map Cart Products */
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  private Set<CartProductEntity> cartProductEntities;

}
