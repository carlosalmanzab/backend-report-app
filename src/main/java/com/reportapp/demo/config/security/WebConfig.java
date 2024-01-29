package com.reportapp.demo.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")// para todos los endpoints
                                .allowedOrigins("/**")// permitir solicitudes de todos los origenes
                                .allowedOriginPatterns("*")// permitir solicitudes de todos los origenes
                                .allowedMethods("*")// permitir todos los
                                .allowedHeaders("*");
 
        }

}
