package com.perfuland.perfulandia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Table(name = "CATEGORIES")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    private String fragancy;

    @NotBlank(message = "El genero no puede estar vacio")
    private String gender;

    @OneToMany(mappedBy = "categoryGender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Perfume> perfumesByGender; // Mapeado correctamente al campo 'categoryGender' en Perfume

    @OneToMany(mappedBy = "categoryFragancy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Perfume> perfumesByFragancy; // Mapeado correctamente al campo 'categoryFragancy' en Perfume
}