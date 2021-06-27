package com.gapstars.assessment.shoppingcart.common.util;

import java.math.BigDecimal;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/** Manages Custom Property File values */
@Getter
@Configuration
@PropertySources({@PropertySource("classpath:shopping-cart.properties")})
public class ShoppingCartProperties {

  /* A generic shipping fee for specific cart **/
  @Value("${shopping.cart.generic.shipping.cost.per.cart}")
  private BigDecimal genericShippingFeePerCart;

  /* A generic shipping fee for specific product **/
  @Value("${shopping.cart.generic.shipping.cost.per.product}")
  private BigDecimal genericShippingFeePerProduct;

}
