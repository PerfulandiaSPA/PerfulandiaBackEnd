package com.perfuland.perfulandia.controller;

import java.util.List;

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
import com.perfuland.perfulandia.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Obtener todas las categorias")
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @Operation(summary = "Obtener categoria por ID")
    @GetMapping("/{idCategory}")
    public Category getCategoryById(@PathVariable Long idCategory) {
        return categoryService.getCategoryById(idCategory);
    }

    @Operation(summary = "Crear una nueva categoria")
    @ApiResponse(responseCode = "201", description = "Categoria creada exitosamente")
    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @Operation(summary = "Actualizar una categoria existente")
    @PutMapping("/{idCategory}")
    public Category updateCategory(@PathVariable Long idCategory, @Valid @RequestBody Category category) {
        return categoryService.updateCategory(idCategory, category);
    }

    @Operation(summary = "Eliminar una categoria")
    @ApiResponse(responseCode = "204", description = "Categoria eliminada exitosamente")
    @DeleteMapping("/{idCategory}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long idCategory) {
        categoryService.deleteCategory(idCategory);
        return ResponseEntity.noContent().build();
    }
}
