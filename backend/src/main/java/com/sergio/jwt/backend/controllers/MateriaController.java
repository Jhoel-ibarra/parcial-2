package com.sergio.jwt.backend.controllers;


import com.sergio.jwt.backend.dtos.MateriaDto;
import com.sergio.jwt.backend.dtos.ModuloDto;
import com.sergio.jwt.backend.services.MateriaService;
import com.sergio.jwt.backend.services.ModuloService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MateriaController {
    private final MateriaService materiaService;


    @GetMapping("/materias")
    public ResponseEntity<List<MateriaDto>> allMateria() {
        return ResponseEntity.ok(materiaService.allMateria());
    }

    @GetMapping("/materia/{id}")
    public ResponseEntity<MateriaDto> getMateria(@PathVariable long id) {
        return ResponseEntity.ok(materiaService.getMateria(id));
    }

    @PostMapping("/materia")
    public ResponseEntity<MateriaDto> createMateria(@Valid @RequestBody MateriaDto materiaDto) {
        MateriaDto createMateria = materiaService.createdMateria(materiaDto);
        return ResponseEntity.created(URI.create("/materias/" + createMateria.getId())).build();
    }

    @PostMapping("/materia/create")
    public ResponseEntity<MateriaDto> creates_Semestre_Materia(@Valid @RequestBody MateriaDto materiaDto) {
        MateriaDto createMateria = materiaService.createMateriaSemestre(materiaDto);
        return ResponseEntity.created(URI.create("/materias/" + createMateria.getId())).build();
    }

    @DeleteMapping("/materia/{id}")
    public ResponseEntity<MateriaDto> deleteMateria(@PathVariable long id) {
        return ResponseEntity.ok(materiaService.deleteMateria(id));

    }

    @PutMapping("/materia/{id}")
    public ResponseEntity<MateriaDto> updateMateria(@PathVariable long id, @Valid @RequestBody MateriaDto materiaDto) {
        return ResponseEntity.ok(materiaService.updateModulo(id, materiaDto));
    }





}
