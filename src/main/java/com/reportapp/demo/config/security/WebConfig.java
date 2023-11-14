package com.reportapp.demo.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.PathItem.HttpMethod;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")// para todos los endpoints
                .allowedOriginPatterns("*")// permitir solicitudes de todos los origenes
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name())// permitir todos los metodos
                .allowedHeaders(HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION)
                .allowedHeaders("*")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Authorization");

        registry.addMapping("/ws/**") // For WebSocket endpoint
                .allowedOriginPatterns("*")
                .allowedHeaders("*") // Allow all headers for WebSocket
                .exposedHeaders("*") // Expose all headers for WebSocket
                .allowCredentials(true);
    }

}
