package com.perfuland.perfulandia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.perfuland.perfulandia.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByIdCategoryContainingIgnoreCase(Long id_category);

    boolean existsByIdCategory(Long id_category);
}
