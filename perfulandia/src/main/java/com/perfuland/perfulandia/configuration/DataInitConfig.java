package com.perfuland.perfulandia.configuration;

import com.perfuland.perfulandia.model.User;
import com.perfuland.perfulandia.model.Category;
import com.perfuland.perfulandia.repository.UserRepository;
import com.perfuland.perfulandia.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitConfig {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, CategoryRepository categoryRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Crear categorías si no existen
            if (categoryRepository.count() == 0) {
                // Categorías de género
                Category hombre = new Category();
                hombre.setGender("Hombre");
                categoryRepository.save(hombre);
                System.out.println(">>> Categoría HOMBRE creada");

                Category mujer = new Category();
                mujer.setGender("Mujer");
                categoryRepository.save(mujer);
                System.out.println(">>> Categoría MUJER creada");

                Category unisex = new Category();
                unisex.setGender("Unisex");
                categoryRepository.save(unisex);
                System.out.println(">>> Categoría UNISEX creada");
            }

            // Si no hay usuarios, creamos admin y user
            if (userRepository.count() == 0) {
                // Crear usuario ADMIN
                User admin = new User();
                admin.setUserName("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setEmail("admin@perfulandia.com");
                admin.setAddress("Central");
                admin.setRole("ROLE_ADMIN");
                userRepository.save(admin);
                System.out.println(">>> Usuario ADMIN creado: admin / admin123");

                // Crear usuario USER
                User user = new User();
                user.setUserName("user");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setEmail("user@perfulandia.com");
                user.setAddress("Domicilio");
                user.setRole("ROLE_USER");
                userRepository.save(user);
                System.out.println(">>> Usuario USER creado: user / user123");
            }
        };
    }
}