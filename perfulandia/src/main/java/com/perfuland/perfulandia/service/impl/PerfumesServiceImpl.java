package com.perfuland.perfulandia.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional; // Importación necesaria para Optional

import com.perfuland.perfulandia.model.Perfume;
import com.perfuland.perfulandia.repository.PerfumeRepository;
import com.perfuland.perfulandia.service.PerfumeService;

@Service
@Transactional
public class PerfumesServiceImpl implements PerfumeService {

    private final PerfumeRepository perfumeRepository;

    public PerfumesServiceImpl(PerfumeRepository perfumeRepository) {
        this.perfumeRepository = perfumeRepository;
    }

    // --- MÉTODOS DE LA INTERFAZ ---\r\n

    @Override
    public List<Perfume> getAllPerfumes() {
        return perfumeRepository.findAll();
    }

    @Override
    public Perfume getPerfumeById(Long idPerfume) {
        // CORRECCIÓN: Usa el método estándar findById()
        return perfumeRepository.findById(idPerfume).orElse(null);
    }

    @Override
    public Perfume createPerfume(Perfume perfume) {
        return perfumeRepository.save(perfume);
    }

    @Override
    public Perfume updatePerfume(Long idPerfume, Perfume perfume) {
        // Implementación básica (se recomienda lógica de negocio más compleja)
        Optional<Perfume> existingPerfume = perfumeRepository.findById(idPerfume);
        if (existingPerfume.isPresent()) {
            Perfume p = existingPerfume.get();
            p.setProductName(perfume.getProductName()); // Usa el nuevo campo
            p.setBrand(perfume.getBrand());
            p.setPrice(perfume.getPrice());
            p.setStock(perfume.getStock());
            p.setDescPerfume(perfume.getDescPerfume()); // Usa el nuevo campo
            p.setImage(perfume.getImage());
            p.setSize(perfume.getSize());
            p.setIsActive(perfume.getIsActive()); // Usa el nuevo campo
            
            // Actualizar categoría si viene en la solicitud
            if (perfume.getCategoryGender() != null) {
                p.setCategoryGender(perfume.getCategoryGender());
            }

            return perfumeRepository.save(p);
        }
        return null;
    }

    @Override
    public void deletePerfume(Long idPerfume) {
        perfumeRepository.deleteById(idPerfume);
    }
}