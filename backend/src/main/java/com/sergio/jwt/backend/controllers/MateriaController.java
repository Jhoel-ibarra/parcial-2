package com.sergio.jwt.backend.controllers;


import com.sergio.jwt.backend.dtos.MateriaDto;
import com.sergio.jwt.backend.dtos.ModuloDto;
import com.sergio.jwt.backend.dtos.RequestMateria;
import com.sergio.jwt.backend.repositories.MateriaRespository;
import com.sergio.jwt.backend.services.MateriaService;
import com.sergio.jwt.backend.services.ModuloService;
import com.sergio.jwt.backend.services.cracionDeCarrera;
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
    private final MateriaRespository materiaRespository;
    private final cracionDeCarrera carreraServicio;


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
        return ResponseEntity.created(URI.create("/materias/" + createMateria.getId())).body(createMateria);
    }

    @PostMapping("/materia/create")
    public ResponseEntity<MateriaDto> creates_Semestre_Materia(@Valid @RequestBody MateriaDto materiaDto) {
        System.out.println("hola");
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

    @PostMapping("/postMateria")
    public ResponseEntity<MateriaDto> postMateria( @RequestBody RequestMateria materiaDto) {
            MateriaDto createdMateri = carreraServicio.postMateria(materiaDto.getMateriaDto(),materiaDto.getSemestreDto());
            return ResponseEntity.created(URI.create("/materias/" + createdMateri.getId())).build();
    }





}
