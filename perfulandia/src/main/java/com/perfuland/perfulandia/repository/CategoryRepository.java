package com.perfuland.perfulandia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.perfuland.perfulandia.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}