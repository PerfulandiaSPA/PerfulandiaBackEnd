package com.perfuland.perfulandia.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;

import com.perfuland.perfulandia.model.User;
import com.perfuland.perfulandia.repository.UserRepository;

@Service
public class UsersServiceImpl {
    private final UserRepository userRepository;

    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    List getAllUsers() {
        return userRepository.findAll();
    }

    User getUserById(Long id_user) {
        return userRepository.findById(id_user)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id_user));
    }

    User createUser(User user) {
        return userRepository.save(user);
    }

    void updatePassword(User existingUser, String newPassword) {
        // Generar un nuevo salt
        String newSalt = generateSalt(); // Método para generar un salt aleatorio

        // Hashear la nueva contraseña con el salt
        String newHashedPassword = hashPassword(newPassword, newSalt); // Método para hashear

        // Actualizar los valores en el usuario existente
        existingUser.setPasswordHash(newHashedPassword);
        existingUser.setPasswordSalt(newSalt);

        // Guardar los cambios en la base de datos
        userRepository.save(existingUser); // Asumiendo que tienes un repositorio para persistir
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }

    void deleteUser(Long id_user) {
        userRepository.deleteById(id_user);
    }

    /*
     * lOGIN Y REGISTER
     */
    User loginUser(String user_name, String password) {
        List<User> users = userRepository.findByUser_name(user_name);
        if (users.isEmpty()) {
            throw new RuntimeException("Invalid username or password");
        }
        User user = users.get(0);
        // Verificar la contraseña
        if (user.verifyPassword(password)) {
            return user;
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }

    User RegisterUser(String user_name, String email, String password) {
        User newUser = new User();
        newUser.setUser_name(user_name);
        newUser.setEmail(email);
        newUser.setPlainPassword(password); // Método que genera salt y hash
        return userRepository.save(newUser);
    }

    User UpdatePasswordDTO(Long id_user, String newPassword) {
        User existingUser = getUserById(id_user);
        updatePassword(existingUser, newPassword);
        return existingUser;
    }
    // Recordar implementar esto en front-end y controller

    /*
     * 5. Flujo en la pantalla de "olvidaste tu contraseña"
     * El usuario solicita un cambio de contraseña.
     * Se envía un enlace o código de verificación al correo o teléfono.
     * Una vez verificado, el usuario ingresa la nueva contraseña.
     * El backend procesa la nueva contraseña con el flujo descrito anteriormente.
     * 6. Consideraciones adicionales
     * Seguridad: Nunca guardes contraseñas en texto plano. Usa siempre algoritmos
     * de hashing seguros.
     * Validación: Asegúrate de que la nueva contraseña cumpla con los requisitos de
     * seguridad (longitud mínima, caracteres especiales, etc.).
     * Expiración de enlaces: Si usas un enlace para restablecer la contraseña,
     * asegúrate de que tenga un tiempo de expiración.
     */
}
