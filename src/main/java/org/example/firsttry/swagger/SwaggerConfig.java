package org.example.firsttry.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPI(){
        return new OpenAPI().
                info(
                        new Info().title("First try").
                                version("1.0").
                                description("First try API").
                                contact(
                                        new Contact().
                                                email("pin320@inbox.ru").
                                                name("Maltsev Konstantin")
                                )


                );
    }
}
