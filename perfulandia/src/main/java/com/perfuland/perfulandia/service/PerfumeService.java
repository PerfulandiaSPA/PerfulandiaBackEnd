package com.perfuland.perfulandia.service;

import java.util.List;

import com.perfuland.perfulandia.model.Perfume;

public interface PerfumeService {

    List<Perfume> getAllPerfumes();

    Perfume getPerfumeById(Long id_perfume); // Nuevo nombre estándar

    Perfume createPerfume(Perfume perfume);

    Perfume updatePerfume(Long id_perfume, Perfume perfume);

    void deletePerfume(Long id_perfume);
}