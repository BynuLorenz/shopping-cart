

package com.gapstars.assessment.shoppingcart.config;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/** The class SwaggerConfig is the configuration class for swagger */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi(){

        return  new Docket(DocumentationType.SWAGGER_2).
                globalOperationParameters(Arrays.asList(new ParameterBuilder()
                        .name("Authorization")
                        .description("Description of header")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(false)
                        .build())).
                select().
                apis(RequestHandlerSelectors.basePackage("com.gapstars")).
                build();
    }
}
