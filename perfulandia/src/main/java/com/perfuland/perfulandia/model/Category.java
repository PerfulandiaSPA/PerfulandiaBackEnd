package com.perfuland.perfulandia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
@Table(name = "CATEGORIES")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    @NotBlank(message = "La fragancia no puede estar vacia")
    private String fragancy;

    @NotBlank(message = "El genero no puede estar vacio")
    private String gender;

    // CORRECCIÓN 3: Mapeo Inverso para la relación 'gender' en Perfume.java
    @OneToMany(mappedBy = "gender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Perfume> perfumesByGender;

    // CORRECCIÓN 4: Mapeo Inverso para la relación 'fragancy' en Perfume.java
    @OneToMany(mappedBy = "fragancy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Perfume> perfumesByFragancy;
}