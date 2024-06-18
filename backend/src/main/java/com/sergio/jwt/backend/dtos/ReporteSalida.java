package com.sergio.jwt.backend.dtos;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class ReporteSalida {
    private String nombre;
    private LocalDate fecha;
    private String asistencia;
    private String materia;
    private String grupo;

}
