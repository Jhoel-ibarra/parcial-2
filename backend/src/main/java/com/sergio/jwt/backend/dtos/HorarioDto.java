package com.sergio.jwt.backend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;


import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class HorarioDto {

    private long id;
    private String dia;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime inicia;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime termina;


}
