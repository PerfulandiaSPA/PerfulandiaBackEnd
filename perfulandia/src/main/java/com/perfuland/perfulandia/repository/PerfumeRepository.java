package com.perfuland.perfulandia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perfuland.perfulandia.model.Perfume;

public interface PerfumeRepository extends JpaRepository<Perfume, Long> {

    // CORRECCIÓN FINAL: Usar findById_perfume para que coincida con la propiedad
    // 'id_perfume'
    List<Perfume> findById_perfume(Long id_perfume);

    // Aplicar el mismo cambio aquí
    boolean existsById_perfume(Long id_perfume);
}