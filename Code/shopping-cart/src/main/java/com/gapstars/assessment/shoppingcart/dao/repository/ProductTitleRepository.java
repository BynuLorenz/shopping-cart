package com.gapstars.assessment.shoppingcart.dao.repository;

import com.gapstars.assessment.shoppingcart.dao.entity.CustomerEntity;
import com.gapstars.assessment.shoppingcart.dao.entity.ProductTitleEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/** Repository class for Cart Product Title Entity */
public interface ProductTitleRepository extends JpaRepository <ProductTitleEntity, Long> {

  @Query ( value = " SELECT c FROM  ProductTitleEntity c WHERE c.productTitleName = :name")
  Optional<CustomerEntity> findByProductTitleName(@Param("name") String productTitleName);
}
