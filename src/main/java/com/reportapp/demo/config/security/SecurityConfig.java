package com.reportapp.demo.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.reportapp.demo.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String[] WHITE_LIST = {
        "/v3/api-docs",
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "/swagger-ui.html",
        "/swagger-resources",
        "/swagger-resources/**",
        "/api/auth/**",
        "/api/comunas",
        "/api/barrios",
        "/auth/**",
        "/docs/**",
        "/docs",
        "/ws/**",
        "/api/notificacion",
        "/api/notificacion/**"
    };

    @Bean
        /**
     * Crea una cadena de filtros de seguridad para las solicitudes HTTP.
     *
     * @param  http                   el objeto HttpSecurity utilizado para la configuración
     * @param  jwtAuthenticationFilter el objeto JwtAuthenticationFilter utilizado para la autenticación
     * @param  authProvider           el objeto AuthenticationProvider utilizado para la autenticación
     * @return                        el objeto SecurityFilterChain que representa la cadena de filtros de seguridad
     * @throws Exception              si ocurre un error durante la creación de la cadena de filtros de seguridad
     */
    public SecurityFilterChain securityFilterChain(
        HttpSecurity http,
        JwtAuthenticationFilter jwtAuthFilter,
        AuthenticationProvider authProvider
        ) throws Exception {

        return http
        .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authRequests ->
                    authRequests
                    .requestMatchers(WHITE_LIST).permitAll()
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .anyRequest().authenticated()
        )
        .sessionManagement(sessionManager ->
                    sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authenticationProvider(authProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }
}
