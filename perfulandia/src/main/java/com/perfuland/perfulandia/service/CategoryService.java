package com.perfuland.perfulandia.service;

import java.util.List;
import com.perfuland.perfulandia.model.Category;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(Long idCategory);

    Category createCategory(Category category);

    Category updateCategory(Long idCategory);

    void deleteCategory(Long idCategory);
}
