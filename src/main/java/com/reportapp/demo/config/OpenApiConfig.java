package com.reportapp.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info()
                .title("RESTful API by Carlos Almanza with Spring Boot")
                .version("v1")
                .description("Esta api presta los servicios necesarios para el funcionamiento de report. app")
                .termsOfService("url github")
                .license(new License()
                        .name("Apache 2.0")
                        .url("url de github")
                )
        );
    }
}
