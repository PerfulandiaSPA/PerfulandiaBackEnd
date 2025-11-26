package com.perfuland.perfulandia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.perfuland.perfulandia.model.Perfume;

public interface PerfumeRepository extends JpaRepository<Perfume, Long> {

    // [ELIMINADOS] List<Perfume> getPerfumeById(Long idPerfume);
    // [ELIMINADOS] boolean existsById_perfume(Long idPerfume);

    // Método de búsqueda por el nuevo campo `productName` del modelo Perfume
    List<Perfume> findByProductName(String productName);
}