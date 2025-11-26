package com.perfuland.perfulandia.controller;

import java.util.List;

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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/perfumes")
public class PerfumeController {

    private final PerfumeService perfumeService;

    public PerfumeController(PerfumeService perfumeService) {
        this.perfumeService = perfumeService;
    }

    @Operation(summary = "Obtener todos los perfumes")
    @GetMapping
    public List<Perfume> getAllPerfumes() {
        return perfumeService.getAllPerfumes();
    }

    @Operation(summary = "Obtener perfumes por ID")
    // ... (otras anotaciones)
    @GetMapping("/{idPerfume}") // <-- CORREGIDO: usar idPerfume
    public Perfume getPerfumeById(@PathVariable Long idPerfume) { // <-- CORREGIDO: usar idPerfume
        return perfumeService.getPerfumeById(idPerfume);
    }

    @Operation(summary = "Crear un nuevo perfume")
    @ApiResponse(responseCode = "201", description = "Perfume creado exitosamente")
    @PostMapping
    public ResponseEntity<Perfume> createPerfume(@Valid @RequestBody Perfume perfume) {
        Perfume createdPerfume = perfumeService.createPerfume(perfume);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerfume);
    }

    @Operation(summary = "Actualizar un perfume existente")
    @PutMapping("/{idPerfume}") // <-- CORREGIDO: usar idPerfume
    public Perfume updatePerfume(@PathVariable Long idPerfume, @Valid @RequestBody Perfume perfume) { // <-- CORREGIDO:
                                                                                                      // usar idPerfume
        return perfumeService.updatePerfume(idPerfume, perfume);
    }

    @Operation(summary = "Eliminar un perfume")
    @ApiResponse(responseCode = "204", description = "Perfume eliminado exitosamente")
    @DeleteMapping("/{idPerfume}") // <-- CORREGIDO: usar idPerfume
    public ResponseEntity<Void> deletePerfume(@PathVariable Long idPerfume) { // <-- CORREGIDO: usar idPerfume
        perfumeService.deletePerfume(idPerfume);
        return ResponseEntity.noContent().build();
    }
}