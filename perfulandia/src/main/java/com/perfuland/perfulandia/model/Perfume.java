package com.perfuland.perfulandia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// 1. NUEVO IMPORT NECESARIO
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PERFUMES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Perfume {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idPerfume;

  @NotBlank(message = "El nombre del producto no puede estar vacio")
  private String productName;

  @NotBlank(message = "Es necesario Incluir marca")
  private String brand;

  @NotNull(message = "Es necesario que el perfume contenga precio")
  @Min(value = 0, message = "El precio no puede ser negativo.")
  private Long price;

  @Min(value = 0, message = "El stock no puede ser negativo.")
  @NotNull(message = "El stock es obligatorio.")
  private Integer stock;

  @NotBlank(message = "Debe haber una descripcion en perfume.")
  private String descPerfume;

  @NotBlank(message = "El producto debe incluir una imagen")
  private String image;

  @NotBlank(message = "El producto debe tener tamaño")
  private String size;

  @NotNull(message = "El estado del perfume no puede estar vacio")
  private Boolean isActive;

  @ManyToOne
  @JoinColumn(referencedColumnName = "idCategory")

  @JsonIgnoreProperties("perfumesByGender")
  private Category categoryGender;

}