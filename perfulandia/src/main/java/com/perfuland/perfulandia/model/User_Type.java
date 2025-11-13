package com.perfuland.perfulandia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "user_type")
@Data
public class User_Type {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user_type;

    @NotBlank(message = "La descripción es obligatoria")
    @Column(nullable = false)
    private String desc_user_type;
    
    // Tipos de usuario: CLIENTE, ADMIN, TRABAJADOR
    // ADMIN: CREA TRABAJADORES
    // TRABAJADOR: AGREGA PERFUMES
    // CLIENTE: COMPRA PERFUMES
}
