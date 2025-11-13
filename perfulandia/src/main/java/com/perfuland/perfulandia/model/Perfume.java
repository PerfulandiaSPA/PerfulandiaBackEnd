package com.perfuland.perfulandia.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "PERFUMES")
@Data
public class Perfume {
    @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id_perfume;

    @NotBlank(message = "El nombre del producto no puede estar vacio")
    private String product_name;
    
    @NotBlank(message = "Es necesario Incluir marca")
    private String brand;
    @NotBlank(message = "Es necesario que el perfume contenga precio")
    private Long price;

    @Min( value = 0, message  = "El stock no puede ser negativo.")
    private Integer stock;
    
    @NotBlank(message = "Debe haber una descripcion en perfume.")
    private String desc_perfume;

    @NotBlank(message = "El producto debe incluir una imagen")
    private String image;

    @NotBlank(message = "El producto debe tener tamaño")
    private String size;

    
    @NotBlank(message = "El estado del perfume no puede estar vacio")
    private Boolean desc_state;
    
    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id_category")
    private Category gender;

    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id_category")
    private Category fragancy;
}
