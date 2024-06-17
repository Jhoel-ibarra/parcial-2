package com.sergio.jwt.backend.mappers;


import com.sergio.jwt.backend.dtos.*;
import com.sergio.jwt.backend.entites.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ClasesMapper {

    ClasesMapper INSTANCE = Mappers.getMapper(ClasesMapper.class);

    MateriaDto toMateriaDto(Materia materia);
    AulaDto toAulaDto(Aula aula);
    HorarioDto toHorarioDto(Horario horario);
    GrupoDto toGrupoDto(Grupo grupo);
    PerfilDocenteDto toDocenteDto(User user);

    Materia toMateria(MateriaDto materiaDto);
    Horario toHorario(HorarioDto horarioDto);
    Aula toAula(AulaDto aulaDto);
    Grupo toGrupo(GrupoDto grupoDto);
    User toDocente(PerfilDocenteDto userDto);

    default ClasesDto toClasesDto(Mate_Grupo_Aula_Horario clases) {
        if (clases == null){
            return null;
        }

        return ClasesDto.builder()
                .materia(toMateriaDto(clases.getMateria()))
                .grupo(toGrupoDto(clases.getGrupo()))
                .horario(toHorarioDto(clases.getHorario()))
                .aula(toAulaDto(clases.getAula()))
                .docente(toDocenteDto(clases.getDocente()))
                .build();
    }

     default List<ClasesDto> toClasesDtos(List<Mate_Grupo_Aula_Horario> clases){
        if (clases == null){
            return null;
        }
        return clases.stream()
                .map(this::toClasesDto)
                .collect(Collectors.toList());
     }



    // Mate_Grupo_Aula_Horario toClases(ClasesDto mate);
   // ClasesDto toClasesDto(Mate_Grupo_Aula_Horario mate);
   // List<ClasesDto> toClasesDtos(List<Mate_Grupo_Aula_Horario> mates);
    //void updateClases(@MappingTarget Mate_Grupo_Aula_Horario clases, Mate_Grupo_Aula_Horario mate);
}
