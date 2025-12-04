package com.perfuland.perfulandia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.perfuland.perfulandia.model.User;
import com.perfuland.perfulandia.repository.UserRepository;
import com.perfuland.perfulandia.security.JwtService;

import java.util.ArrayList;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String userName, String password) {
        // 1. Buscar usuario
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Verificar password con PasswordEncoder (BCrypt)
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // 3. Generar Token usando JwtService
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                new ArrayList<>()
        );

        return jwtService.generateToken(userDetails);
    }
}