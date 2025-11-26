package com.perfuland.perfulandia.service;

import java.util.List;

import com.perfuland.perfulandia.model.Perfume;

public interface PerfumeService {

    List<Perfume> getAllPerfumes();

    // 🌟 OPCIÓN 1: La mejor práctica, devuelve un Optional para manejar la ausencia
    // Optional<Perfume> getPerfumeById(Long id_perfume);

    // 🌟 OPCIÓN 2: Mantiene la firma simple (devuelve null o lanza excepción si no
    // se encuentra)
    Perfume getPerfumeById(Long id_perfume); // Nuevo nombre estándar

    // Eliminamos: List<Perfume> getPerfumesById(Long id_perfume);
    // Eliminamos: Perfume SearchById(Long id_perfume);

    Perfume createPerfume(Perfume perfume);

    Perfume updatePerfume(Long id_perfume, Perfume perfume);

    void deletePerfume(Long id_perfume);
}