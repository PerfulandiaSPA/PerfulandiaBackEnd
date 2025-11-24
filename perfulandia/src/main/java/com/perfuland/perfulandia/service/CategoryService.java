package com.perfuland.perfulandia.service;
import java.util.List;
import com.perfuland.perfulandia.model.Category;
public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id_category);
    Category createCategory(Category category);
    Category updateCategory(Long id_category);
    void deleteCategory(Long id_category);
}
