package com.perfuland.perfulandia.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                return http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/api/v1/perfumes/**")
                                                .hasAnyAuthority("ROLE_WORKER")
                                                .requestMatchers("/api/v1/orders/**")
                                                .hasAnyAuthority("ROLE_WORKER")
                                                .requestMatchers("/api/v1/categories/**")
                                                .hasAnyAuthority("ROLE_WORKER") // Permitir acceso a
                                                                                // usuarios con rol
                                                                                // WORKER, ADMIN o CLIENT
                                                .requestMatchers("/api/auth/login").permitAll() // Permitir acceso libre
                                                                                                // al endpoint de login
                                                .requestMatchers("/api/v1/users/**")
                                                .hasAnyAuthority("ROLE_ADMIN")
                                                // Permisos para el SWAGGER UI
                                                .requestMatchers("/v3/api-docs/**",
                                                                "/swagger-ui.html",
                                                                "/swagger-ui/**")
                                                .permitAll() // Permitir acceso libre al endpoint de swagger

                                                .anyRequest().authenticated())
                                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .build();
        }

}
