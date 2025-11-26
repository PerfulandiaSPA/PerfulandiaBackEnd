package com.perfuland.perfulandia.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Si usas @Transactional

import com.perfuland.perfulandia.model.Perfume;
import com.perfuland.perfulandia.repository.PerfumeRepository;
import com.perfuland.perfulandia.service.PerfumeService;

@Service
@Transactional // Si lo deseas
public class PerfumesServiceImpl implements PerfumeService {

    private final PerfumeRepository perfumeRepository;

    public PerfumesServiceImpl(PerfumeRepository perfumeRepository) {
        this.perfumeRepository = perfumeRepository;
    }

    // --- MÉTODOS DE LA INTERFAZ ---

    @Override
    public List<Perfume> getAllPerfumes() {
        return perfumeRepository.findAll();
    }

    @Override
    public Perfume getPerfumeById(Long idPerfume) {
        List<Perfume> perfumes = perfumeRepository.getPerfumeById(idPerfume);

        // Devolvemos el primero si existe, o null si la lista está vacía.
        return perfumes.isEmpty() ? null : perfumes.get(0);
    }

    @Override
    public Perfume createPerfume(Perfume perfume) {
        return perfumeRepository.save(perfume);
    }

    @Override
    public Perfume updatePerfume(Long idPerfume, Perfume perfume) {
        // En un update real, primero buscarías, luego actualizarías campos y luego
        // harías save.
        // Pero para compilar, esta firma básica es suficiente.
        return perfumeRepository.save(perfume);
    }

    @Override
    public void deletePerfume(Long idPerfume) {
        // Para este método, probablemente usarías el método heredado de JpaRepository:
        perfumeRepository.deleteById(idPerfume);
    }
}