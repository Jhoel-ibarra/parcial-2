package com.sergio.jwt.backend.controllers;


import com.sergio.jwt.backend.dtos.LicenciaDto;
import com.sergio.jwt.backend.dtos.MateriaDto;
import com.sergio.jwt.backend.dtos.SemestreDto;
import com.sergio.jwt.backend.dtos.UserDto;
import com.sergio.jwt.backend.entites.Licencia;
import com.sergio.jwt.backend.services.LicenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("licencia")
@RestController
public class LicenciaCOntroller {

    private final LicenciaService licenciaService;

    @GetMapping("/all")
    public ResponseEntity<List<LicenciaDto>> listasDocente(long id) {
        return ResponseEntity.ok(licenciaService.allDocente(id));
    }

    @PostMapping("/crear/{id}")
    public ResponseEntity<LicenciaDto> createLicencia(@RequestBody LicenciaDto licenciaDto, @PathVariable long id) {
       LicenciaDto licencia = licenciaService.createLicencia(licenciaDto, id);
       return ResponseEntity.created(URI.create("/licencia/" + licencia.getId())).body(licencia);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LicenciaDto> getLicencia(@PathVariable long id) {
        return ResponseEntity.ok(licenciaService.getLicencia(id));
    }




}
