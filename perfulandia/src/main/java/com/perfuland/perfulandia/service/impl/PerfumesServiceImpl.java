package com.perfuland.perfulandia.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.perfuland.perfulandia.model.Perfume;
import com.perfuland.perfulandia.repository.PerfumeRepository;
import com.perfuland.perfulandia.service.PerfumeService; // Importar la interfaz
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PerfumesServiceImpl implements PerfumeService { // <-- ¡Implementar la interfaz!
    // En PerfumesServiceImpl.java
    // En PerfumesServiceImpl.java
    private final PerfumeRepository perfumeRepository;

    public PerfumesServiceImpl(PerfumeRepository perfumeRepository) {
        this.perfumeRepository = perfumeRepository;
    }

    // Nota: Los métodos de la interfaz DEBEN ser públicos.

    @Override
    public List<Perfume> getAllPerfumes() {
        return perfumeRepository.findAll();
    }

    @Override
    public Perfume createPerfume(Perfume perfume) {
        return perfumeRepository.save(perfume);
    }

    @Override
    public Perfume updatePerfume(Long id_perfume, Perfume perfume) {
        Perfume existingPerfume = perfumeRepository.findById(id_perfume)
                .orElseThrow(() -> new RuntimeException("Perfume not found"));
        existingPerfume.setProduct_name(perfume.getProduct_name());
        existingPerfume.setBrand(perfume.getBrand());
        existingPerfume.setPrice(perfume.getPrice());
        existingPerfume.setStock(perfume.getStock());
        existingPerfume.setDesc_perfume(perfume.getDesc_perfume());
        existingPerfume.setImage(perfume.getImage());
        existingPerfume.setDesc_state(perfume.getDesc_state());
        existingPerfume.setSize(perfume.getSize());
        existingPerfume.setGender(perfume.getGender());
        existingPerfume.setFragancy(perfume.getFragancy()); // <-- Asumiendo que también quieres actualizar fragancy
        return perfumeRepository.save(existingPerfume);
    }

    @Override
    public void deletePerfume(Long id_perfume) {
        perfumeRepository.deleteById(id_perfume);
    }
}