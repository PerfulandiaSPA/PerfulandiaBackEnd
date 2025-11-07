package com.perfuland.perfulandia.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.perfuland.perfulandia.model.Perfume;
import com.perfuland.perfulandia.repository.PerfumeRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PerfumeServiceImpl {
    private final PerfumeRepository perfumeRepository;

    public PerfumeServiceImpl(PerfumeRepository perfumeRepository) {
        this.perfumeRepository = perfumeRepository;
    }

    //   List<Perfume> getAllPerfumes();
    // List<Perfume> getPerfumesById(Long id_perfume);
    // Perfume SearchById(Long id_perfume);
    // Perfume createPerfume(Perfume perfume);
    // Perfume updatePerfume(Long id_perfume, Perfume perfume);
    // void deletePerfume(Long id_perfume);

List<Perfume> getAllPerfumes(){
        return perfumeRepository.findAll();
    }
    List<Perfume> getPerfumesById(Long id_perfume){
        return perfumeRepository.findByIdOrderContainingIgnoreCase(id_perfume);
    }
   public Perfume searchById(Long id_perfume){
         return perfumeRepository.findById(id_perfume)
         .orElseThrow(() -> new RuntimeException("Perfume not found"));
   }
    Perfume createPerfume(Perfume perfume){
        return perfumeRepository.save(perfume);
    }
    Perfume updatePerfume(Long id_perfume, Perfume perfume){
        Perfume existingPerfume = perfumeRepository.findById(id_perfume).orElseThrow(() -> new RuntimeException("Perfume not found"));
        existingPerfume.setProduct_name(perfume.getProduct_name());
        existingPerfume.setBrand(perfume.getBrand());
        existingPerfume.setPrice(perfume.getPrice());
        existingPerfume.setStock(perfume.getStock());
        existingPerfume.setDesc_perfume(perfume.getDesc_perfume());
        existingPerfume.setImage(perfume.getImage());
        existingPerfume.setDesc_state(perfume.getDesc_state());
        existingPerfume.setSize(perfume.getSize());
        existingPerfume.setGender(perfume.getGender());
        return perfumeRepository.save(existingPerfume);
    }
    void deletePerfume(Long id_perfume){
        perfumeRepository.deleteById(id_perfume);
    }
}


