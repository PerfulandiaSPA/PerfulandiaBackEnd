package com.perfuland.perfulandia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Table(name = "CATEGORIES")
@Entity
public class Category {
    @NotBlank(message = "El id de la categoria no puede estar vacio")
    private String id_category;

    @NotBlank(message = "La fragancia no puede estar vacia")
    private String fragancy;
    
    @NotBlank(message = "El genero no puede estar vacio")
    private String gender;
    
}