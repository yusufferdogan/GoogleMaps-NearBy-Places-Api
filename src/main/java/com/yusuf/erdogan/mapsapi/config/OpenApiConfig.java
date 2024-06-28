package com.yusuf.erdogan.mapsapi.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Places API")
                        .version("1.0")
                        .description("API for managing places and their requests")
                .termsOfService("http://swagger.io/terms/")
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://springdoc.org")
                )
                .contact(new Contact()
                        .email("yusuffx03@gmail.com")
                        .name("AHMET YUSUF ERDOGAN")
                        .url("https://www.linkedin.com/in/yusuf--erdogan/")
                ));
    }
}
