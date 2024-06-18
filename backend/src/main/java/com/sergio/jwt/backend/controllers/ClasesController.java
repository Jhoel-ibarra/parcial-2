package com.sergio.jwt.backend.controllers;


import com.sergio.jwt.backend.dtos.*;
import com.sergio.jwt.backend.services.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class ClasesController {

    private final AulaService aulaService;
    private final MateriaService materiaService;
    private final ModuloService moduloService;
    private final HorarioService horarioService;
    private final ClaseService claseService;
    private final GrupoService grupoService;


    @GetMapping("/aula")
    public ResponseEntity<List<AulaDto>> allAula(){

        return ResponseEntity.ok(aulaService.getAllAulas());
    }

    @PostMapping("/aula")
    public ResponseEntity<AulaDto> createAula(@Valid @RequestBody AulaDto aulaDto){
        AulaDto createdAula = aulaService.createAula(aulaDto);
        return ResponseEntity.created(URI.create( "/aula/" + createdAula.getId())).body(createdAula);
    }

    @PostMapping("/horario")
    public ResponseEntity<HorarioDto> createHorario(@Valid @RequestBody HorarioDto horarioDto){
        System.out.println(horarioDto);
        HorarioDto horario = horarioService.addHorario(horarioDto);
        return ResponseEntity.created(URI.create( "/horario/" +horario.getId())).body(horario);
    }

    @PostMapping("/grupo")
    public ResponseEntity<GrupoDto> createGrupo(@Valid @RequestBody GrupoDto grupoDto){
        GrupoDto grupo = grupoService.addGrupo(grupoDto);
        return ResponseEntity.created(URI.create( "/grupo/" +grupo.getId())).body(grupo);
    }


    @GetMapping("/aula/{id}")
    public ResponseEntity<AulaDto> getAulaId(@PathVariable long id){
        return ResponseEntity.ok(aulaService.getAula(id));
    }

    @GetMapping("horario/{id}")
    public ResponseEntity<HorarioDto> getHorarioId(@PathVariable long id){
        return ResponseEntity.ok(horarioService.getHorario(id));
    }
    @GetMapping("/grupo/{id}")
    public ResponseEntity<GrupoDto> getGrupoId(@PathVariable long id){
        return ResponseEntity.ok(grupoService.getGrupo(id));
    }



    @PostMapping("/clase")
    public ResponseEntity<ClasesDto> ClaseCrear(@Valid @RequestBody ClasesEntradaDto clasesDto){
        ClasesDto clases = claseService.nuevoClase(clasesDto);
        return ResponseEntity.created(URI.create( "/clase/" +clases.getId())).build();

    }

    @GetMapping("/clase/{id}")
    public ResponseEntity<ClasesDto> getClaseId(@PathVariable long id){
        return ResponseEntity.ok(claseService.getClaseId(id));
    }

    @GetMapping("/clases")
    public ResponseEntity<List<ClasesDto>> allClases(){
        return ResponseEntity.ok(claseService.allClases());
    }


}
