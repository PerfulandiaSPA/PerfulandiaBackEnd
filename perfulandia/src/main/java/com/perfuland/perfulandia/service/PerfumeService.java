package com.perfuland.perfulandia.service;

import java.util.List;

import com.perfuland.perfulandia.model.Perfume;

public interface PerfumeService {

    List<Perfume> getAllPerfumes();

    Perfume getPerfumeById(Long idPerfume); // Nuevo nombre estándar

    Perfume createPerfume(Perfume perfume);

    Perfume updatePerfume(Long idPerfume, Perfume perfume);

    void deletePerfume(Long idPerfume);
}