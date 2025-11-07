package com.perfuland.perfulandia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.perfuland.perfulandia.model.Perfume;

public interface PerfumeRepository extends JpaRepository<Perfume, Long> {
    List<Perfume> findByIdOrderContainingIgnoreCase(Long id_Perfume);
    boolean existsByIdOrder(Long id_Perfume);
}
