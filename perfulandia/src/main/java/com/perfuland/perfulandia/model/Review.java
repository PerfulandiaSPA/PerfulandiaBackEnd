package com.perfuland.perfulandia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReview;

    @NotBlank(message = "El título no puede estar vacío.")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Este campo no puede estar vacío.")
    @Column(nullable = false)
    private String content;

    @NotNull(message = "La puntuación no puede estar vacía.")
    @Min(value = 1, message = "La puntuación mínima es 1.")
    @Max(value = 5, message = "La puntuación máxima es 5.")
    @Column(nullable = false)
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user; // <-- CORREGIDO de user_name

    @ManyToOne
    @JoinColumn(name = "id_perfume", nullable = false)
    private Perfume perfume;
}