package com.perfuland.perfulandia.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                                                .requestMatchers(HttpMethod.GET, "/api/v1/perfumes/**").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/v1/users/**").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll() 

                                                //.requestMatchers("/api/v1/orders/**").hasAnyAuthority("ROLE_WORKER")
                                                //.requestMatchers("/api/v1/categories/**").permitAll()

                                                // Permisos para el SWAGGER UI
                                                .requestMatchers("/v3/api-docs/**",
                                                                "/swagger-ui.html",
                                                                "/swagger-ui/**")
                                                .permitAll() 

                                                .anyRequest().authenticated())
                                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .build();
        }

}
