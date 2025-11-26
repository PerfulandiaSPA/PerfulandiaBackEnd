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
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/perfumes")
public class PerfumeController {

    private final PerfumeService perfumeService;

    public PerfumeController(PerfumeService perfumeService) {
        this.perfumeService = perfumeService;
    }

    @Operation(summary = "Obtener todos los perfumes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de perfumes obtenidos exitosamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Perfume.class))))
    })
    @GetMapping
    public List<Perfume> getAllPerfumes() {
        return perfumeService.getAllPerfumes();
    }

    @Operation(summary = "Obtener perfumes por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Perfume obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Perfume no encontrado")
    })
    @GetMapping("/{id}")
    public Perfume getPerfumeById(@PathVariable Long id_perfume) {
        return perfumeService.getPerfumeById(id_perfume);
    }

    @Operation(summary = "Crear un nuevo perfume")
    @ApiResponse(responseCode = "201", description = "Perfume creado exitosamente")
    @PostMapping
    public ResponseEntity<Perfume> createPerfume(@Valid @RequestBody Perfume perfume) {
        Perfume createdPerfume = perfumeService.createPerfume(perfume);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerfume);
    }

    @Operation(summary = "Actualizar un perfume existente")
    @PutMapping("/{id}")
    public Perfume updatePerfume(@PathVariable Long id_perfume, @Valid @RequestBody Perfume perfume) {
        return perfumeService.updatePerfume(id_perfume, perfume);
    }

    @Operation(summary = "Eliminar un perfume")
    @ApiResponse(responseCode = "204", description = "Perfume eliminado exitosamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerfume(@PathVariable Long id_perfume) {
        perfumeService.deletePerfume(id_perfume);
        return ResponseEntity.noContent().build();
    }

}
