package com.gapstars.assessment.shoppingcart.dao.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/** Entity class for Customer */
@Entity
@Getter
@Setter
@Table(name = "customer")
public class CustomerEntity extends AuditableEntity {

  /** Property related to customer name */
  @Column(name="customer_first_name")
  private String firstName;

  /** Property related to customer last name */
  @Column(name="customer_last_name")
  private String lastName;

  /** Relationship to Map Cart Cart */
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
  private CartEntity cartEntity;

}
