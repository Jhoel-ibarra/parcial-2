package com.sergio.jwt.backend.dtos;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class HorarioDto {

    private long id;
    private String dia;
    private LocalDate HoraInicio;
    private LocalDate HoraFinal;

}
