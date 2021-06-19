package com.gapstars.assessment.shoppingcart.dao.repository;

import com.gapstars.assessment.shoppingcart.dao.entity.CartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repository class for Cart Product Entity */
public interface CartProductRepository extends JpaRepository<CartProductEntity, Long> {

}
