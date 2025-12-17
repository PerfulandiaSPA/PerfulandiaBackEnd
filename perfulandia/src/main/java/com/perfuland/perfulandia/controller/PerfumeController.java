package com.perfuland.perfulandia.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.perfuland.perfulandia.model.Perfume;
import com.perfuland.perfulandia.service.PerfumeService;
import com.perfuland.perfulandia.dto.PerfumeDTO;
import com.perfuland.perfulandia.dto.PerfumeCreateDTO;
import com.perfuland.perfulandia.dto.CategoryDTO;
import com.perfuland.perfulandia.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/perfumes")
public class PerfumeController {

    private final PerfumeService perfumeService;
    private final CategoryService categoryService;

    public PerfumeController(PerfumeService perfumeService, CategoryService categoryService) {
        this.perfumeService = perfumeService;
        this.categoryService = categoryService;
    }

    // Método auxiliar para mapear Perfume a PerfumeDTO
    private PerfumeDTO mapToPerfumeDTO(Perfume perfume) {
        if (perfume == null) return null;
        
        PerfumeDTO dto = new PerfumeDTO();
        dto.setIdPerfume(perfume.getIdPerfume());
        dto.setProductName(perfume.getProductName());
        dto.setBrand(perfume.getBrand());
        dto.setPrice(perfume.getPrice());
        dto.setStock(perfume.getStock());
        dto.setDescPerfume(perfume.getDescPerfume());
        dto.setImage(perfume.getImage());
        dto.setSize(perfume.getSize());
        dto.setIsActive(perfume.getIsActive());
        
        if (perfume.getCategoryGender() != null) {
            CategoryDTO catDTO = new CategoryDTO();
            catDTO.setIdCategory(perfume.getCategoryGender().getIdCategory());
            catDTO.setGender(perfume.getCategoryGender().getGender());
            dto.setCategoryGender(catDTO);
        }
        
        return dto;
    }

    @Operation(summary = "Obtener todos los perfumes")
    @GetMapping
    public List<PerfumeDTO> getAllPerfumes() {
        return perfumeService.getAllPerfumes().stream()
                .map(this::mapToPerfumeDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener perfumes por ID")
    @GetMapping("/{idPerfume}")
    public ResponseEntity<PerfumeDTO> getPerfumeById(@PathVariable Long idPerfume) {
        Perfume perfume = perfumeService.getPerfumeById(idPerfume);
        if (perfume == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapToPerfumeDTO(perfume));
    }

    @Operation(summary = "Crear un nuevo perfume")
    @ApiResponse(responseCode = "201", description = "Perfume creado exitosamente")
    @PostMapping
    public ResponseEntity<PerfumeDTO> createPerfume(@Valid @RequestBody PerfumeCreateDTO perfumeCreateDTO) {
        // Convertir DTO a entidad
        Perfume perfume = new Perfume();
        perfume.setProductName(perfumeCreateDTO.getProductName());
        perfume.setBrand(perfumeCreateDTO.getBrand());
        perfume.setPrice(perfumeCreateDTO.getPrice());
        perfume.setStock(perfumeCreateDTO.getStock());
        perfume.setDescPerfume(perfumeCreateDTO.getDescPerfume());
        perfume.setImage(perfumeCreateDTO.getImage());
        perfume.setSize(perfumeCreateDTO.getSize());
        perfume.setIsActive(perfumeCreateDTO.getIsActive());
        
        // Obtener y asignar la categoría por ID
        if (perfumeCreateDTO.getCategoryGenderId() != null) {
            com.perfuland.perfulandia.model.Category category = 
                categoryService.getCategoryById(perfumeCreateDTO.getCategoryGenderId());
            perfume.setCategoryGender(category);
        }
        
        Perfume createdPerfume = perfumeService.createPerfume(perfume);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToPerfumeDTO(createdPerfume));
    }

    @Operation(summary = "Actualizar un perfume existente")
    @PutMapping("/{idPerfume}")
    public ResponseEntity<PerfumeDTO> updatePerfume(@PathVariable Long idPerfume, @Valid @RequestBody Perfume perfume) {
        Perfume updatedPerfume = perfumeService.updatePerfume(idPerfume, perfume);
        if (updatedPerfume == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapToPerfumeDTO(updatedPerfume));
    }

    @Operation(summary = "Eliminar un perfume")
    @ApiResponse(responseCode = "204", description = "Perfume eliminado exitosamente")
    @DeleteMapping("/{idPerfume}")
    public ResponseEntity<Void> deletePerfume(@PathVariable Long idPerfume) {
        perfumeService.deletePerfume(idPerfume);
        return ResponseEntity.noContent().build();
    }
}