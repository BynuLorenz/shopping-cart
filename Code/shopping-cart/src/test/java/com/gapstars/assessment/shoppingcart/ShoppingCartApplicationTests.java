package com.gapstars.assessment.shoppingcart;

import com.gapstars.assessment.shoppingcart.common.property.ShoppingCartProperties;
import com.gapstars.assessment.shoppingcart.controller.payload.response.CartAmountResponse;
import com.gapstars.assessment.shoppingcart.service.CustomerService;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShoppingCartApplicationTests {

	@Autowired
	CustomerService customerService;
	@Autowired
	ShoppingCartProperties properties;

	@Test
	void checkCartAmounts() {

		CartAmountResponse response = customerService.calculateCartAmounts(2L);
		//assertEquals(response.getTotalCartAmount(), new BigDecimal(1353.15));
		assertEquals(response.getShippingAmount(), new BigDecimal(100));
	}

	@Test
	void checkPropertyFile() {
		assertEquals( properties.getGenericShippingFee(), new BigDecimal( 100.00 ) );
	}

}
