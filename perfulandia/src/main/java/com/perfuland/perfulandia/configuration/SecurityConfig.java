package com.perfuland.perfulandia.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// IMPORTACIONES DE CORS
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import com.perfuland.perfulandia.security.JwtAuthenticationFilter;
import java.util.Arrays;
import java.util.List;
// FIN DE IMPORTACIONES DE CORS

@Configuration
public class SecurityConfig {
        
        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        
        public SecurityConfig(@Lazy JwtAuthenticationFilter jwtAuthenticationFilter) {
                this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                return http
                                // 1. HABILITAR CORS Y APLICAR LA CONFIGURACIÓN DEL BEAN
                                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(HttpMethod.GET, "/api/v1/perfumes/**").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/v1/users/**").permitAll()
                                                .requestMatchers("/api/auth/login").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/api/v1/perfumes/**").hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.PUT, "/api/v1/perfumes/**").hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.DELETE, "/api/v1/perfumes/**").hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.POST, "/api/v1/categories/**").hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.DELETE, "/api/v1/categories/**").hasRole("ADMIN")
                                                // Permisos para el SWAGGER UI
                                                .requestMatchers("/v3/api-docs/**",
                                                                "/swagger-ui.html",
                                                                "/swagger-ui/**")
                                                .permitAll()

                                                .anyRequest().authenticated())
                                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                .build();
        }

        // 2. BEAN PARA DEFINIR LAS REGLAS DE CORS
        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                // 🔑 Origen de tu frontend React
                configuration.setAllowedOrigins(List.of("http://localhost:3000"));
                // Métodos necesarios para las operaciones (GET, POST, PUT, DELETE)
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                // Permite cualquier encabezado, incluyendo el Authorization para el token JWT
                configuration.setAllowedHeaders(List.of("*"));
                // Necesario para enviar cookies o encabezados de autenticación (como el JWT)
                configuration.setAllowCredentials(true);

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }

        // 3. BEAN PARA PASSWORD ENCODER
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}