package com.perfuland.perfulandia.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
// IMPORTACIONES DE CORS
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
import java.util.List;
// FIN DE IMPORTACIONES DE CORS

@Configuration
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                return http
                                // 1. HABILITAR CORS Y APLICAR LA CONFIGURACIÓN DEL BEAN
                                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(HttpMethod.GET, "/api/v1/perfumes/**").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/v1/users/**").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()

                                                // Permisos para el SWAGGER UI
                                                .requestMatchers("/v3/api-docs/**",
                                                                "/swagger-ui.html",
                                                                "/swagger-ui/**")
                                                .permitAll()

                                                .anyRequest().authenticated())
                                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                // 💡 NOTA: Aquí deberías añadir tu filtro JWT:
                                // .addFilterBefore(jwtAuthenticationFilter,
                                // UsernamePasswordAuthenticationFilter.class)
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

        // 💡 NOTA: Recuerda que necesitas el resto de tus Beans (PasswordEncoder,
        // AuthenticationManager, etc.)
        // y la inyección del JwtAuthenticationFilter para que la seguridad funcione
        // completamente.
}