package com.perfuland.perfulandia.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.perfuland.perfulandia.model.Category;
import com.perfuland.perfulandia.repository.CategoryRepository;
import com.perfuland.perfulandia.service.CategoryService; // <-- Importar interfaz

@Service
public class CategoriesServiceImpl implements CategoryService { // <-- Implementa la interfaz
    private final CategoryRepository categoryRepository;

    public CategoriesServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long idCategory) {
        return categoryRepository.findById(idCategory).orElse(null);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long idCategory, Category category) { // <-- Corregido firma
        Category existingCategory = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + idCategory));

        // Actualización de campos
        existingCategory.setFragancy(category.getFragancy());
        existingCategory.setGender(category.getGender());
        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long idCategory) {
        categoryRepository.deleteById(idCategory);
    }
}