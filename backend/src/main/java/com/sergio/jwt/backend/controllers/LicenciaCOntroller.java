package com.sergio.jwt.backend.controllers;


import com.sergio.jwt.backend.dtos.LicenciaDto;
import com.sergio.jwt.backend.dtos.UserDto;
import com.sergio.jwt.backend.entites.Licencia;
import com.sergio.jwt.backend.services.LicenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController("/licencia")
public class LicenciaCOntroller {

    private final LicenciaService licenciaService;

    @GetMapping("/all")
    public ResponseEntity<List<LicenciaDto>> findAll(
            @AuthenticationPrincipal UserDto userDto) {

        return ResponseEntity.ok(licenciaService.getAll(userDto));
    }

    @PostMapping
    public ResponseEntity<LicenciaDto> createLicencia(
            @AuthenticationPrincipal UserDto user,
            @RequestBody LicenciaDto licenciaDto) {
        return ResponseEntity.created(URI.create("/licencia/all")).body(licenciaService.createLicencia(licenciaDto, user));
    }

}
