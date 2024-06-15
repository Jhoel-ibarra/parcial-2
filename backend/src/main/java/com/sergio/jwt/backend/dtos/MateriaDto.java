package com.sergio.jwt.backend.dtos;

import com.sergio.jwt.backend.entites.Semestre;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class MateriaDto {

    private Long id;
    private String sigla;
    private String name;
    private long semestreId;
    private List<GrupoDto> grupos;

}
