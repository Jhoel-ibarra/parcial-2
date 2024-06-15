package com.sergio.jwt.backend.dtos;

import com.sergio.jwt.backend.entites.Carrera;
import com.sergio.jwt.backend.entites.Materia;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DocenteDto {

    private PerfilDocenteDto docente;
    private List<MateriaDto> mateiaList;

}
