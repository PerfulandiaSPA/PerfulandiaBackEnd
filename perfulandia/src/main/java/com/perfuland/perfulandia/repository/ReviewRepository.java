package com.perfuland.perfulandia.repository;

import com.perfuland.perfulandia.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // el extends trae todos los métodos CRUD básicos, no es necesario declararlos aquí
}

