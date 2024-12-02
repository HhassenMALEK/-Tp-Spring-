package com.fr.diginamic.documentation;

import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ville par Département")
                        .version("1.0")
                        .description("Cette API fournit des des données sur les ville, les communes et les départements de France")
                        .contact(new Contact().name("Hassen MALEK").email("h.hassen.malek@gmail.com")));
    }
}