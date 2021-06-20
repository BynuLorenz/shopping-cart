package com.gapstars.assessment.shoppingcart;

import com.gapstars.assessment.shoppingcart.common.dto.CustomerDto;
import com.gapstars.assessment.shoppingcart.common.dto.ProductDto;
import com.gapstars.assessment.shoppingcart.common.enums.ResponseCode;
import com.gapstars.assessment.shoppingcart.service.CustomerService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ShoppingCartApplication implements CommandLineRunner {

	@Autowired( required = false )
	CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

	@Override
	public void run(String... args)  {

			// Assessment Task 1
			createTwoCustomers();
			// Assessment Task 2
			addProductToFirstCustomer();
			// Assessment Task 3
			calculateFirstCustomerCartAmounts();
			// Assessment Task 4
			addProductsToSecondCustomer();
			// Assessment Task 5
			calculateSecondCustomerCartAmounts();
	}

	/**
	 * Assessment Task 1 - Create new Customers
	 */
	void createTwoCustomers () {

		log.info( "Start execution - createTwoCustomers()" );

		CustomerDto customerDto = new CustomerDto();
		customerDto.setFirstName( "John W." );
		customerDto.setLastName( "Smith" );
		customerService.createCustomer( customerDto );

		customerDto = new CustomerDto();
		customerDto.setFirstName( "Will" );
		customerDto.setLastName( "Graham" );
		customerService.createCustomer( customerDto );

		log.info( "Execution of createTwoCustomers() successfully completed." );
	}

	/**
	 * Assessment Task 2 - Add products to first customer's cart
	 */
	void addProductToFirstCustomer () {

		log.info("Start execution - addProductToFirstCustomer()");
		ProductDto fereroProduct = new ProductDto();
		fereroProduct.setProductId( 1L );

		List<ProductDto> productDtos = new ArrayList<>();
		productDtos.add( fereroProduct );
		customerService.addProductsToCart( productDtos , 1L );
		log.info( "Execution of addProductToFirstCustomer() successfully completed." );
	}

	/**
	 * Assessment Task 3 - Calculate amounts of first customer
	 */
	void calculateFirstCustomerCartAmounts () {

		log.info( "Start execution - calculateFirstCustomerCartAmounts()" );
		customerService.calculateCartAmounts(1L);
		log.info( "Execution of calculateFirstCustomerCartAmounts() successfully completed." );
	}

	/**
	 * Assessment Task 3 - add products to second customer's cart
	 */
	void addProductsToSecondCustomer () {

		log.info("Start execution - addProductsToSecondCustomer()");
		ProductDto tobleroneProduct = new ProductDto();
		tobleroneProduct.setProductId( 2L );

		ProductDto kitKatProduct = new ProductDto();
		kitKatProduct.setProductId( 3L );

		List<ProductDto> productDtos = new ArrayList<>();
		productDtos.add( tobleroneProduct );
		productDtos.add( kitKatProduct );

		CustomerDto customerDto = new CustomerDto();
		customerDto.setFirstName( "Second" );
		customerService.addProductsToCart( productDtos , 2L );
		log.info( "Execution of addProductsToSecondCustomer() successfully completed." );

	}

	/**
	 * Assessment Task 3 - Calculate amounts of second customer
	 */
	void calculateSecondCustomerCartAmounts () {

		log.info( "Start execution - calculateSecondCustomerCartAmounts()" );
		customerService.calculateCartAmounts( 2L );
		log.info( "Execution of calculateSecondCustomerCartAmounts() successfully completed." );

	}
}
