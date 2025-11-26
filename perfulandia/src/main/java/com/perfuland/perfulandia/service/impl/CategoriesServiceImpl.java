package com.perfuland.perfulandia.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.perfuland.perfulandia.model.Category;
import com.perfuland.perfulandia.repository.CategoryRepository;

@Service
public class CategoriesServiceImpl {
    private final CategoryRepository categoryRepository;

    public CategoriesServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    // List<Category> getAllCategories();
    // Category getCategoryById(Long id_category);
    // Category createCategory(Category category);
    // Category updateCategory(Long id_category, Category category);
    // void deleteCategory(Long id_category);

    List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    Category getCategoryById(Long idCategory) {
        return categoryRepository.findById(idCategory).orElse(null);
    }

    Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    Category updateCategory(Long idCategory, Category category) {
        Category existingCategory = categoryRepository.findById(idCategory).orElse(null);
        existingCategory.setFragancy(category.getFragancy());
        existingCategory.setGender(category.getGender());
        return categoryRepository.save(existingCategory);
    }

    void deleteCategory(Long idCategory) {
        categoryRepository.deleteById(idCategory);
    }
}
