package com.perfuland.perfulandia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

// Librerías de seguridad: Lo ideal es mover la lógica de hash a una clase de servicio o utilidad.
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    // Sugerencia: Añadir unique = true si es el campo de inicio de sesión.
    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false) // Si se usa como login, añadir , unique = true
    private String user_name;

    @NotBlank(message = "La dirección es obligatoria ")
    @Column(nullable = false)
    private String address;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico no es válido")
    @Column(nullable = false, unique = true)
    private String email;

    // contraseña (hash)
    @Column(nullable = false)
    private String passwordHash;

    // salt para la contraseña (Base64)
    @Column(nullable = false)
    private String passwordSalt;

    /**
     * Establece la contraseña en texto plano: genera salt y hash y los guarda.
     */
    public void setPlainPassword(String plainPassword) {
        String salt = generateSalt();
        this.passwordSalt = salt;
        this.passwordHash = hashPassword(plainPassword, salt);
    }

    /**
     * Verifica si la contraseña en texto plano coincide con el hash almacenado.
     */
    public boolean verifyPassword(String plainPassword) {
        if (this.passwordSalt == null || this.passwordHash == null)
            return false;
        String computed = hashPassword(plainPassword, this.passwordSalt);
        return computed.equals(this.passwordHash);
    }

    /*
     * Generamos un salt aleatorio para la contraseña.
     */
    private String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    /*
     * Generamos el hash de la contraseña usando PBKDF2 con HMAC SHA-256.
     */
    private String hashPassword(String password, String saltBase64) {
        try {
            byte[] salt = Base64.getDecoder().decode(saltBase64);
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el hash de la contraseña", e);
        }
    }

    // Tipos de usuario
    @NotBlank(message = "El tipo de usuario es obligatorio")
    @Column(nullable = false)
    private String user_type;

    // CORRECCIÓN 5: Mapeo Inverso para la relación Review.user_name
    @OneToMany(mappedBy = "user_name", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;

}