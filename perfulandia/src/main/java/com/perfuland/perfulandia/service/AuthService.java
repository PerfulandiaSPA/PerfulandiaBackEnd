package com.perfuland.perfulandia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.perfuland.perfulandia.model.User;
import com.perfuland.perfulandia.repository.UserRepository;
import com.perfuland.perfulandia.security.JwtService; // Importar el servicio correcto (como en biblioteca)
// import com.perfuland.perfulandia.configuration.JwtUtil; // <--- BORRAR O COMENTAR ESTO

import java.util.ArrayList;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService; // Usamos JwtService en lugar de JwtUtil

    public String login(String userName, String password) {
        // 1. Buscar usuario
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Verificar password (en perfulandia es texto plano, en biblioteca es BCrypt)
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // 3. Generar Token usando JwtService
        // IMPORTANTE: JwtService espera un UserDetails, debemos adaptarlo
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                new ArrayList<>() // O convertir user.getRole() a GrantedAuthority
        );

        return jwtService.generateToken(userDetails);
    }
}