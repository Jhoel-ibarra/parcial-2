package com.sergio.jwt.backend.dtos;

import com.sergio.jwt.backend.entites.Mate_Grupo_Aula_Horario;
import jakarta.persistence.Column;

import java.time.LocalDate;

public class AsistenciaDto {


    private long id;

    private String estado;

    private LocalDate fecha;

    private String fotoUrl;

    private double longitud;

    private double latitud;

    private String descripcion;

    private ClasesDto clase;
}
