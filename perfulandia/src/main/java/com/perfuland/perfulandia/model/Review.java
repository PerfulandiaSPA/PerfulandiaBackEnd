package com.perfuland.perfulandia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// 1. IMPORTANTE: Agrega este import para que funcionen las anotaciones JSON
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReview;

    private String title;

    @NotBlank(message = "El comentario no puede estar vacío.")
    @Column(nullable = false)
    private String content;

    @NotNull(message = "La puntuación no puede estar vacía.")
    @Min(value = 1, message = "La puntuación mínima es 1.")
    @Max(value = 5, message = "La puntuación máxima es 5.")
    @Column(nullable = false)
    private Integer rating;

    // --- CORRECCIÓN AQUÍ ---
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    // Esto evita que al traer el user, traiga sus reviews y password de vuelta
    @JsonIgnoreProperties({ "reviews", "password", "hibernateLazyInitializer", "handler" })
    private User user;

    // --- CORRECCIÓN AQUÍ ---
    @ManyToOne
    @JoinColumn(name = "id_perfume", nullable = false)
    // Esto es CRUCIAL: "Trae el perfume, pero NO traigas la lista de reviews que
    // tiene dentro"
    @JsonIgnoreProperties({ "reviews", "hibernateLazyInitializer", "handler" })
    private Perfume perfume;
}