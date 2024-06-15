package com.sergio.jwt.backend.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class HorarioDto {

    private long id;
    private String dia;
    private String HoraInicio;
    private String HoraFinal;

}
