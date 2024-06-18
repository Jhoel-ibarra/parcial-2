package com.sergio.jwt.backend.dtos;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class ReporteEntrada {

    private long user_id;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private String asistencia_estado;
    private String materia;
    private String grupo;

}
