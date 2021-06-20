package com.gapstars.assessment.shoppingcart.dao.repository;


import com.gapstars.assessment.shoppingcart.dao.entity.ProductEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/** Repository class for Product Entity */
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

  @Query( value = " SELECT c FROM  ProductEntity c WHERE c.productName = :productName")
  Optional<ProductEntity> findByProductName ( @Param( "productName" ) String productName ) ;

}
