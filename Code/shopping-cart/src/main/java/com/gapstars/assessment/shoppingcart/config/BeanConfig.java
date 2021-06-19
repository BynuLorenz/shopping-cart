package com.gapstars.assessment.shoppingcart.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Application bean configuration class */
@Configuration
public class BeanConfig {

  /**
   * Maintain application bean - model mapper
   * @return Model Mapper instance
   */
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

}
