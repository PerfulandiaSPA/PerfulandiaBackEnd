package com.perfuland.perfulandia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfumeDTO {
    private Long idPerfume;
    private String productName;
    private String brand;
    private Long price;
    private Integer stock;
    private String descPerfume;
    private String image;
    private String size;
    private Boolean isActive;
    private CategoryDTO categoryGender;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryDTO {
        private Long idCategory;
        private String gender;
    }
}
