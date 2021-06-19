package com.gapstars.assessment.shoppingcart.dao.repository;

import com.gapstars.assessment.shoppingcart.dao.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repository class for Cart Entity */
public interface CartRepository extends JpaRepository<CartEntity, Long> {

}
