package com.perfuland.perfulandia.configuration;

import com.perfuland.perfulandia.model.User;
import com.perfuland.perfulandia.repository.CategoryRepository;
import com.perfuland.perfulandia.repository.PerfumeRepository;
import com.perfuland.perfulandia.repository.UserRepository;
import com.perfuland.perfulandia.model.Category;
import com.perfuland.perfulandia.model.Perfume;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitConfig {

    private final CategoryRepository categoryRepository;

    DataInitConfig(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CategoryRepository categoryRepository,
            PerfumeRepository perfumeRepository) {
        return args -> {

            // --- BLOQUE 1: TUS USUARIOS (INTACTO) ---
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

            // --- BLOQUE 2: CATEGORÍAS Y PERFUMES ---
            if (categoryRepository.count() == 0) {

                // 1. Crear Categoría HOMBRE
                Category catHombre = new Category(); // Cambié el nombre de var a 'catHombre' para ser claro
                catHombre.setGender("Hombre");
                // Guardamos y recuperamos la instancia guardada (que ya tiene ID)
                catHombre = categoryRepository.save(catHombre);

                // 2. Crear Categoría MUJER
                Category catMujer = new Category();
                catMujer.setGender("Mujer");
                catMujer = categoryRepository.save(catMujer);

                System.out.println(">>> Categorias iniciales creadas");

                // --- BLOQUE 3: AHORA CREAMOS LOS PERFUMES USANDO ESAS CATEGORÍAS ---

                // Perfume 1 (Mujer)
                Perfume p1 = new Perfume();
                p1.setProductName("Black Opium");
                p1.setBrand("Yves Saint Laurent");
                p1.setPrice(12351L);
                p1.setStock(50);
                p1.setDescPerfume("Dulce y café");
                p1.setImage("black_opium.jpg");
                p1.setSize("90ml");
                p1.setIsActive(true);
                p1.setCategoryGender(catMujer); // Usamos la variable catMujer de arriba

                // Perfume 2 (Mujer)
                Perfume p2 = new Perfume();
                p2.setProductName("Good Girl");
                p2.setBrand("Carolina Herrera");
                p2.setPrice(98000L);
                p2.setStock(20);
                p2.setDescPerfume("Floral y audaz");
                p2.setImage("good_girl.jpg");
                p2.setSize("80ml");
                p2.setIsActive(true);
                p2.setCategoryGender(catMujer); // También Mujer

                // Perfume 3 (Hombre)
                Perfume p3 = new Perfume();
                p3.setProductName("Sauvage");
                p3.setBrand("Dior");
                p3.setPrice(110000L);
                p3.setStock(30);
                p3.setDescPerfume("Fresco y picante");
                p3.setImage("sauvage.jpg");
                p3.setSize("100ml");
                p3.setIsActive(true);
                p3.setCategoryGender(catHombre); // Usamos catHombre

                // Guardamos todos los perfumes
                perfumeRepository.saveAll(List.of(p1, p2, p3));
                System.out.println(">>> Perfumes iniciales creados y vinculados.");
            }
        };
    }
}