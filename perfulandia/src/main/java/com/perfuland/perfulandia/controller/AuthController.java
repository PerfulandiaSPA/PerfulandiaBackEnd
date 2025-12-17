package com.perfuland.perfulandia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfuland.perfulandia.dto.LoginDTO;
import com.perfuland.perfulandia.dto.RegisterDTO;
import com.perfuland.perfulandia.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
// --- ELIMINA ESTA LÍNEA ---
// import io.swagger.v3.oas.annotations.parameters.RequestBody; 

// --- AGREGA ESTA LÍNEA ---
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth Controller", description = "Controlador para autenticación y autorización de usuarios")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Iniciar sesión", description = "Permite a un usuario iniciar sesión y obtener un token de acceso.")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        String token = authService.login(
                loginDTO.getUserName(),
                loginDTO.getPassword());
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "Registrar usuario", description = "Permite registrar un nuevo usuario en el sistema.")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO request) {
        try {
            // ... lógica ...
            authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado con éxito");
        } catch (RuntimeException e) {
            // Aquí es donde Spring devuelve el 400 si el usuario ya existe
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}