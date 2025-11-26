package com.perfuland.perfulandia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

// librerias contraseña
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
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
     * Verifica si la contraseña en texto plano coincide con el hash almacenado,
     * osea la contraseña del usuario que lo creo con el hash guardado en la base de
     * datos
     * lo que quiere decir que si alguien intenta iniciar sesión con su contraseña
     * se genera el hash de esa contraseña y se compara con el hash almacenado
     */
    public boolean verifyPassword(String plainPassword) {
        if (this.passwordSalt == null || this.passwordHash == null)
            return false;
        String computed = hashPassword(plainPassword, this.passwordSalt);
        return computed.equals(this.passwordHash);
    }

    /*
     * Generamos un salt aleatorio para la contraseña para que sea más segura y
     * difícil de atacar el salt aleatorio
     * quiere decir que cada usuario tendrá un salt diferente aunque tengan la misma
     * contraseña
     * EJ: "password123" y el salt seria como "XyZ123AbC456EfG7"
     */
    private String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    /*
     * Generamos el hash de la contraseña usando PBKDF2 con HMAC SHA-256 para
     * debilear ataques de fuerza bruta y decodificación
     * al decir decodificacion nos referimos a que si alguien obtiene la base de
     * datos no pueda obtener las contraseñas en texto plano
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

    // Tipos de usuario: CLIENTE, ADMIN, TRABAJADOR
    // ADMIN: CREA TRABAJADORES
    // TRABAJADOR: AGREGA PERFUMES
    // CLIENTE: COMPRA PERFUMES
    @NotBlank(message = "El tipo de usuario es obligatorio")
    @Column(nullable = false)
    private String user_type;

}
