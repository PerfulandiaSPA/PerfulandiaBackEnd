package com.perfuland.perfulandia.configuration;

import com.perfuland.perfulandia.model.User;
import com.perfuland.perfulandia.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitConfig {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            // Si no hay usuarios, creamos el admin (Igual que en DataInitConfig de biblioteca)
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setUserName("admin");
                admin.setPassword("12345"); // Nota: En biblioteca usas encoder, aquí texto plano por ahora
                admin.setEmail("admin@perfulandia.com");
                admin.setAddress("Central");
                admin.setRole("ROLE_ADMIN"); // String directo según tu modelo User
                
                userRepository.save(admin);
                System.out.println(">>> Usuario ADMIN creado: admin / 12345");
            }
        };
    }
}