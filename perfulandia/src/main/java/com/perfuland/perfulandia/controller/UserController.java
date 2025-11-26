package com.perfuland.perfulandia.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.perfuland.perfulandia.dto.LoginDTO;
import com.perfuland.perfulandia.dto.RegisterDTO;
import com.perfuland.perfulandia.dto.UpdatePasswordDTO;
import com.perfuland.perfulandia.model.User;
import com.perfuland.perfulandia.service.UsersService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    // Se recomienda inyección por constructor (final) en lugar de @Autowired en el
    // campo
    private final UsersService userService;

    public UserController(UsersService userService) {
        this.userService = userService;
    }

    // Endpoint para obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Endpoint para obtener un usuario por ID
    @GetMapping("/{idUser}") // <-- CORREGIDO: usar idUser
    public ResponseEntity<User> getUserById(@PathVariable Long idUser) { // <-- CORREGIDO: usar idUser
        User user = userService.getUserById(idUser);
        return ResponseEntity.ok(user);
    }

    // Endpoint para crear un nuevo usuario
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Endpoint para actualizar un usuario
    @PutMapping("/{idUser}") // <-- CORREGIDO: usar idUser
    public ResponseEntity<User> updateUser(@PathVariable Long idUser, @RequestBody User user) { // <-- CORREGIDO: usar
                                                                                                // idUser
        User updatedUser = userService.updateUser(idUser, user);
        return ResponseEntity.ok(updatedUser);
    }

    // Endpoint para eliminar un usuario
    @DeleteMapping("/{idUser}") // <-- CORREGIDO: usar idUser
    public ResponseEntity<Void> deleteUser(@PathVariable Long idUser) { // <-- CORREGIDO: usar idUser
        userService.deleteUser(idUser);
        return ResponseEntity.noContent().build();
    }

    /*
     * ENDPOINTS ADICIONALES PARA LOGIN Y ACTUALIZACIÓN DE CONTRASEÑA
     */

    // Endpoint para iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        // CORREGIDO: Usa el nuevo getter userName del DTO
        User user = userService.loginUser(loginDTO.getUserName(), loginDTO.getPassword());

        // Si no se encontró o la contraseña es inválida, el servicio lanza una
        // excepción.
        // Si llega aquí, el login fue exitoso.
        return ResponseEntity.ok("Login exitoso");
    }

    // Endpoint para registrar un usuario (Usando el DTO de registro)
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterDTO registerDTO) {
        User registeredUser = userService.RegisterUser(
                registerDTO.getUserName(),
                registerDTO.getEmail(),
                registerDTO.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    // Endpoint para actualizar la contraseña
    @PutMapping("/{idUser}/password") // <-- CORREGIDO: usar idUser
    public ResponseEntity<Void> updatePassword(@PathVariable Long idUser, // <-- CORREGIDO: usar idUser
            @RequestBody UpdatePasswordDTO updatePasswordDTO) {
        userService.updatePassword(idUser, updatePasswordDTO.getNewPassword());
        return ResponseEntity.noContent().build();
    }
}