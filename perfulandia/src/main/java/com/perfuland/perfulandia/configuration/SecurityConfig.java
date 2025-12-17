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

@Configuration
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthenticationFilter;

        public SecurityConfig(@Lazy JwtAuthenticationFilter jwtAuthenticationFilter) {
                this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                return http
                                // 1. HABILITAR CORS USANDO TU CONFIGURACIÓN
                                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                                // 2. DESHABILITAR CSRF (Común en APIs REST stateless)
                                .csrf(csrf -> csrf.disable())

                                // 3. REGLAS DE AUTORIZACIÓN
                                .authorizeHttpRequests(auth -> auth
                                                // --- RUTAS PÚBLICAS (GET) ---
                                                .requestMatchers(HttpMethod.GET, "/api/v1/perfumes/**").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/v1/users/**").permitAll()

                                                // --- AUTENTICACIÓN ---
                                                .requestMatchers("/api/auth/login").permitAll()

                                                // --- LOGICA DE GUEST / INVITADOS (REVIEWS) ---
                                                // Permitimos VER (GET) reviews a todos
                                                .requestMatchers(HttpMethod.GET, "/api/v1/reviews/**").permitAll()
                                                // Permitimos CREAR (POST) reviews a todos (Login no requerido)
                                                // --- RUTAS PROTEGIDAS (ADMIN) ---
                                                .requestMatchers(HttpMethod.POST, "/api/v1/perfumes/**")
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.PUT, "/api/v1/perfumes/**").hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.DELETE, "/api/v1/perfumes/**")
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.POST, "/api/v1/reviews/").hasRole("USER")
                                                .requestMatchers(HttpMethod.POST, "/api/v1/categories/**")
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.DELETE, "/api/v1/categories/**")
                                                .hasRole("ADMIN")

                                                // --- SWAGGER UI (Documentación) ---
                                                .requestMatchers(
                                                                "/v3/api-docs/**",
                                                                "/swagger-ui.html",
                                                                "/swagger-ui/**")
                                                .permitAll()

                                                // --- CUALQUIER OTRA RUTA REQUIERE AUTENTICACIÓN ---
                                                .anyRequest().authenticated())

                                // 4. GESTIÓN DE SESIÓN (Stateless para JWT)
                                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                                // 5. FILTRO JWT
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                .build();
        }

        // 2. BEAN PARA DEFINIR LAS REGLAS DE CORS (Tus reglas originales)
        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                // Origen de tu frontend React
                configuration.setAllowedOrigins(List.of("http://localhost:3000"));
                // Métodos permitidos
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                // Headers permitidos
                configuration.setAllowedHeaders(List.of("*"));
                // Credenciales
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