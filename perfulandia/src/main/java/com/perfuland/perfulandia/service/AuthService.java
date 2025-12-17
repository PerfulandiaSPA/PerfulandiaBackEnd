package com.perfuland.perfulandia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.perfuland.perfulandia.dto.RegisterDTO;
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
                new ArrayList<>());

        return jwtService.generateToken(userDetails);
    }

    // CAMBIO IMPORTANTE: Ahora recibimos RegisterRequest en lugar de User
    public void register(RegisterDTO request) {
        // Validar si el usuario existe
        if (userRepository.findByUserName(request.getUserName()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        // Validar si el email existe (Opcional pero recomendado)
        if (userRepository.findByEmail(request.getEmail()).isPresent()) { // Asumiendo que creaste este método en el
                                                                          // repo
            throw new RuntimeException("El email ya está registrado");
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail()); // <--- GUARDAMOS EL EMAIL
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Dejamos address como null o vacío porque ahora es opcional
        user.setAddress("");

        userRepository.save(user);
    }
}