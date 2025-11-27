package com.perfuland.perfulandia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfuland.perfulandia.configuration.JwtUtil;
import com.perfuland.perfulandia.model.User;
import com.perfuland.perfulandia.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String userName, String password) {
        // buscando un usuario por su nombre de usuario
        User user = UserRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // verificando si la contraseña proporcionada coincide con la almacenada
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // generando un token JWT para el usuario autenticado
        return jwtUtil.generateToken(user.getUserName(), user.getRole());
    }
}
