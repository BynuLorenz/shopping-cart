package com.gapstars.assessment.shoppingcart.common.property;

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

  @Value("${shopping.cart.generic.shipping.cost}")
  private BigDecimal genericShippingFee;

}
