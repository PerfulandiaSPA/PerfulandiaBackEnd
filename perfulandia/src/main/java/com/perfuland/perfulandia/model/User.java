package com.perfuland.perfulandia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, unique = true) // Agregada restricción de unicidad para el login
    private String userName; // <-- CORREGIDO de user_name

    @NotBlank(message = "La dirección es obligatoria ")
    @Column(nullable = false)
    private String address;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico no es válido")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String passwordSalt;

    @NotBlank(message = "El tipo de usuario es obligatorio")
    @Column(nullable = false)
    private String userType; // <-- CORREGIDO de user_type

    // Relaciones
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // Mapeado a 'user' en Review.java
    private List<Review> reviews;

    // --- Lógica de Seguridad (Se mantienen, aunque idealmente irían en un
    // servicio) ---

    public void setPlainPassword(String plainPassword) {
        if (plainPassword == null || plainPassword.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }
        this.passwordSalt = generateSalt();
        this.passwordHash = hashPassword(plainPassword, this.passwordSalt);
    }

    public boolean checkPassword(String plainPassword) {
        if (plainPassword == null || this.passwordHash == null)
            return false;
        String computed = hashPassword(plainPassword, this.passwordSalt);
        return computed.equals(this.passwordHash);
    }

    private String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

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
}