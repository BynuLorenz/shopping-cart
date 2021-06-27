package com.gapstars.assessment.shoppingcart;

import com.gapstars.assessment.shoppingcart.common.dto.CustomerDto;
import com.gapstars.assessment.shoppingcart.common.dto.ProductDto;
import com.gapstars.assessment.shoppingcart.common.util.ShoppingCartProperties;
import com.gapstars.assessment.shoppingcart.controller.payload.response.AddProductsResponse;
import com.gapstars.assessment.shoppingcart.controller.payload.response.CartAmountResponse;
import com.gapstars.assessment.shoppingcart.controller.payload.response.CustomerResponse;
import com.gapstars.assessment.shoppingcart.controller.payload.response.ProductResponse;
import com.gapstars.assessment.shoppingcart.dao.entity.CartEntity;
import com.gapstars.assessment.shoppingcart.dao.entity.CustomerEntity;
import com.gapstars.assessment.shoppingcart.dao.entity.ProductEntity;
import com.gapstars.assessment.shoppingcart.dao.repository.CartRepository;
import com.gapstars.assessment.shoppingcart.dao.repository.CustomerRepository;
import com.gapstars.assessment.shoppingcart.dao.repository.ProductRepository;
import com.gapstars.assessment.shoppingcart.service.CustomerService;
import com.gapstars.assessment.shoppingcart.service.ProductService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShoppingCartApplicationTests {

	@Autowired
	CustomerService customerService;
	@Autowired
	ShoppingCartProperties properties;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	ProductService productService;

	@Test
	void createCustomer () {

		CustomerDto customerDto = new CustomerDto();
		customerDto.setFirstName( "Test" );
		customerDto.setLastName( "Test" );
		CustomerResponse response = customerService.createCustomer( customerDto ) ;
		Assertions.assertThat( response.getId() != 0 );

	}

	@Test
	@Transactional
	void findCustomerById () {

		Optional<CustomerEntity> entity= customerRepository.findById( 1L );
		assertEquals( entity.get().getFirstName(), "John W." );
	}

	@Test
	@Transactional
	void findCartById () {

		Optional<CartEntity> cartEntity = cartRepository.findById( 1L );
		assertEquals( cartEntity.get().getId(), 1L );
	}

	@Test
	@Transactional
	void findCartIdByCustomer () {

		Optional<CustomerEntity> customerEntity = customerRepository.findById( 1L );
		assertEquals( customerEntity.get().getCartEntity().getId(), 1L );
	}

	@Test
	@Transactional
	void findProductById () {

		Optional<ProductEntity> productEntity = productRepository.findById( 1L );
		assertEquals( productEntity.get().getProductName(), "KITKAT" );
	}

	@Test
	void addProductsToCart () {

		List<ProductDto> productDtos = new ArrayList<>();
		ProductDto dto = new ProductDto();
		dto.setProductId( 1L );
		productDtos.add( dto );

		AddProductsResponse response = customerService.addProductsToCart( productDtos, 1L );
		assertEquals( response.getCartId(), 1L );
	}

	@Test
	@Transactional
	void findProductTitleByProductIdId () {

		Optional<ProductEntity> productEntity = productRepository.findById( 1L );
		assertEquals( productEntity.get().getProductTitle().getProductTitleName(), "CHOCOLATE" );
	}

	@Test
	void checkCartAmounts () {

		CartAmountResponse response = customerService.getCartAmounts(2L);
		//assertEquals(response.getTotalCartAmount(), new BigDecimal(1353.15));
		assertEquals(response.getShippingAmount(), new BigDecimal(100));
	}

	@Test
	void checkPropertyFile () {
		assertEquals( properties.getGenericShippingFeePerProduct(), new BigDecimal( 100 ) );
	}

	@Test
	void calculateCartAmounts () {

		CartAmountResponse response = customerService.getCartAmounts( 2L );
		Assertions.assertThat( response.getTotalCartAmount() != BigDecimal.ZERO );
	}

	@Test
	void getAllCustomers () {

		List<CustomerResponse> responses = customerService.getAllCustomers();
		Assertions.assertThat( responses.size() != 0 );
	}

	@Test
	void getAllProducts () {

		List<ProductResponse> responses = productService.getAllProducts();
		Assertions.assertThat( responses.size() != 0 );
	}

	@Test
	void addProduct () {

		Optional<ProductEntity> productEntity = productRepository.findByProductName( "OREO" );
		assertNotNull( productEntity );

		ProductDto dto = new ProductDto();
		dto.setPrice( new BigDecimal( 100.00) );
		dto.setProductName( "Creamy" );
		dto.setProductTitle( productEntity.get().getProductTitle().getId() );
		dto.setProductQuantity( new BigDecimal( 500 ) );
		dto.setTax( new BigDecimal( 3.2 ) );

		ProductResponse response = productService.createProduct( dto  );
		assertNotNull( response.getProductId() );
	}

}
