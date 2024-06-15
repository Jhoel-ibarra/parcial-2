package com.sergio.jwt.backend.dtos;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class ClasesDto {

    private long id;
    private MateriaDto materia;
    private GrupoDto grupo;
    private HorarioDto horario;
    private AulaDto aula;
    private PerfilDocenteDto docente;
}
