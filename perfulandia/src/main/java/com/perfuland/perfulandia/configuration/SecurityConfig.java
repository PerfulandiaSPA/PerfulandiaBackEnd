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
                        .requestMatchers("/api/v1/categories/**")
                        .hasAnyAuthority("ROLE_WORKER")
                        .requestMatchers("/api/v1//**")
                        .hasAnyAuthority("ROLE_WORKER") // Permitir acceso a
                                                      // usuarios con rol
                                                      // Worker
                        .requestMatchers("/api/auth/login").permitAll() // Permitir acceso libre
                                                                        // al endpoint de login
                        .requestMatchers("/api/users/**")
                        .hasAnyAuthority("ROLE_ADMIN")
                        // Permisos para el SWAGGER UI
                        .requestMatchers("/doc/swagger-ui/**",
                                "/doc/swagger-ui/index.html",
                                "/doc/swagger-ui/swagger-ui.css",
                                "/doc/swagger-ui/swagger-ui-bundle.js",
                                "/doc/swagger-ui/swagger-ui-standalone-preset.js",
                                "/doc/swagger-ui/index.css",
                                "/doc/swagger-ui/swagger-initializer.js",
                                "/doc/swagger-ui/favicon-32x32.png",
                                "/v3/api-docs/swagger-config",
                                "/v3/api-docs")
                        .permitAll() // Permitir acceso libre al endpoint de swagger

                        .anyRequest().authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}
