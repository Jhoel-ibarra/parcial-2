package com.sergio.jwt.backend.controllers;

import com.sergio.jwt.backend.dtos.ModuloDto;
import com.sergio.jwt.backend.dtos.SemestreDto;
import com.sergio.jwt.backend.services.SemestreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController

public class SemestreController {

    private final SemestreService semestreService;

    @GetMapping("/semestres")
    public ResponseEntity<List<SemestreDto>> allSemestres() {
        return ResponseEntity.ok(semestreService.allSemestres());
    }

    @GetMapping("/semestre/{id}")
    public ResponseEntity<SemestreDto> getSemestre(@PathVariable long id) {
        return ResponseEntity.ok(semestreService.getSemestre(id));
    }

    @PostMapping("/semestre")
    public ResponseEntity<SemestreDto> createSemestre(@Valid @RequestBody SemestreDto semestreDto) {
        SemestreDto semestre = semestreService.createSemestre(semestreDto);
        return ResponseEntity.created(URI.create("/semestre/" + semestre.getId())).body(semestre);
    }
    @PutMapping("/semestre/{id}")
    public ResponseEntity<SemestreDto> updateSemestre(@PathVariable long id, @Valid @RequestBody SemestreDto semestreDto) {
        return ResponseEntity.ok(semestreService.updateSemestre(id, semestreDto));
    }


}
