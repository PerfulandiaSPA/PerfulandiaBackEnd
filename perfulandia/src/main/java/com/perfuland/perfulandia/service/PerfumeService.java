package com.perfuland.perfulandia.service;

import java.util.List;


import com.perfuland.perfulandia.model.Perfume;

public interface PerfumeService {

    List<Perfume> getAllPerfumes();
    List<Perfume> getPerfumesById(Long id_perfume);
    Perfume SearchById(Long id_perfume);
    Perfume createPerfume(Perfume perfume);
    Perfume updatePerfume(Long id_perfume, Perfume perfume);
    void deletePerfume(Long id_perfume);
}
