package com.gapstars.assessment.shoppingcart.dao.repository;

import com.gapstars.assessment.shoppingcart.dao.entity.CustomerEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/** Repository class for Cart Customer Entity */
public interface CustomerRepository extends JpaRepository <CustomerEntity, Long> {

  @Query ( value = " SELECT c FROM  CustomerEntity c WHERE c.firstName = :firstName")
  Optional<CustomerEntity> findByFirstName ( @Param("firstName") String firstName);
}
