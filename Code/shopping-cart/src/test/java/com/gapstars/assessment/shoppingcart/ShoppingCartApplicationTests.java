package com.gapstars.assessment.shoppingcart;

import com.gapstars.assessment.shoppingcart.dao.entity.ProductEntity;
import com.gapstars.assessment.shoppingcart.dao.repository.ProductRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShoppingCartApplicationTests {

	@Autowired
	ProductRepository productRepository ;

	@Test
	void saveProducts() {



	}

}
