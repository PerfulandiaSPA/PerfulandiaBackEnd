package com.perfuland.perfulandia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "CATEGORIES")
@Entity
public class Category {

    private String id_category;

    private String fragancy;

    private String gender;
    
}