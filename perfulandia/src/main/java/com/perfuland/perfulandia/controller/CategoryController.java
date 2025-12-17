package com.perfuland.perfulandia.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfuland.perfulandia.model.Category;
import com.perfuland.perfulandia.dto.CategoryDTO;
import com.perfuland.perfulandia.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    // Método auxiliar para mapear Category a CategoryDTO
    private CategoryDTO mapToCategoryDTO(Category category) {
        if (category == null) return null;
        return new CategoryDTO(category.getIdCategory(), category.getGender());
    }

    @Operation(summary = "Obtener todas las categorias")
    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories().stream()
                .map(this::mapToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener categoria por ID")
    @GetMapping("/{idCategory}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long idCategory) {
        Category category = categoryService.getCategoryById(idCategory);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapToCategoryDTO(category));
    }

    @Operation(summary = "Crear una nueva categoria")
    @ApiResponse(responseCode = "201", description = "Categoria creada exitosamente")
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToCategoryDTO(createdCategory));
    }

    @Operation(summary = "Actualizar una categoria existente")
    @PutMapping("/{idCategory}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long idCategory, @Valid @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(idCategory, category);
        if (updatedCategory == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapToCategoryDTO(updatedCategory));
    }

    @Operation(summary = "Eliminar una categoria")
    @ApiResponse(responseCode = "204", description = "Categoria eliminada exitosamente")
    @DeleteMapping("/{idCategory}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long idCategory) {
        categoryService.deleteCategory(idCategory);
        return ResponseEntity.noContent().build();
    }
}
