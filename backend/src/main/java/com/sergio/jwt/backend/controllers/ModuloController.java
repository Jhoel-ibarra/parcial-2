package com.sergio.jwt.backend.controllers;

import com.sergio.jwt.backend.dtos.ModuloDto;
import com.sergio.jwt.backend.services.ModuloService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ModuloController {

    private final ModuloService moduloService;

    @GetMapping("/modulos")
    public ResponseEntity<List<ModuloDto>> allModulos(){
        return ResponseEntity.ok(moduloService.allModulos());
    }

    @GetMapping("/modulos/{id}")
    public ResponseEntity<ModuloDto> getModulo(@PathVariable long id){
        return ResponseEntity.ok(moduloService.getModulo(id));
    }

    @PostMapping("/modulos")
    public ResponseEntity<ModuloDto> createModulo(@Valid @RequestBody ModuloDto moduloDto){
        ModuloDto createdModulo = moduloService.createModulo(moduloDto);
        return ResponseEntity.created(URI.create("/modulos/" + createdModulo.getId())).body(createdModulo);
    }
    @DeleteMapping("/modulos/{id}")
    public ResponseEntity<ModuloDto> deleteModulo(@PathVariable long id){
        return ResponseEntity.ok(moduloService.deleteModulo(id));
    }
    @PutMapping("/modulos/{id}")
    public ResponseEntity<ModuloDto> updateModulo(@PathVariable long id, @Valid @RequestBody ModuloDto moduloDto){
        return ResponseEntity.ok(moduloService.updateModulo(id, moduloDto));
    }
}
