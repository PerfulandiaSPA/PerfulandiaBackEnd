package com.perfuland.perfulandia.service;

import java.util.List;
import com.perfuland.perfulandia.model.Category;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(Long idCategory);

    Category createCategory(Category category);

    // <-- CORREGIDO: Faltaba el objeto Category en la firma
    Category updateCategory(Long idCategory, Category category);

    void deleteCategory(Long idCategory);
}