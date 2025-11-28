package com.perfuland.perfulandia.configuration;

import com.perfuland.perfulandia.model.Role;
import com.perfuland.perfulandia.model.User;
import com.perfuland.perfulandia.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class DataInitConfig {
    
    @Bean
    CommandLineRunner initUsers(UserRepository userRepository,
                                PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                User worker = User.builder()
                        .userName("worker")
                        .password(passwordEncoder.encode("worker123"))
                        .role(Role.ROLE_WORKER)
                        .build();
                userRepository.save(worker);

                User admin = User.builder()
                        .userName("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .role(Role.ROLE_ADMIN)
                        .build();
                userRepository.save(admin);

                User client = User.builder()
                        .userName("client")
                        .password(passwordEncoder.encode("client123"))
                        .role(Role.ROLE_CLIENT)
                        .build();
                userRepository.save(client);
            }
        };
    }
}
