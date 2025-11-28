package com.perfuland.perfulandia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import lombok.Builder;

@Entity
@Table(name = "USERS") // Evitar conflicto con palabra reservada 'USER'
@Data
@Builder
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
    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // Relaciones
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // Mapeado a 'user' en Review.java
    private List<Review> reviews;

}