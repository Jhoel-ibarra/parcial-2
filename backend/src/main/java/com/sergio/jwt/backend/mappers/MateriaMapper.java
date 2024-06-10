package com.sergio.jwt.backend.mappers;

import com.sergio.jwt.backend.dtos.MateriaDto;
import com.sergio.jwt.backend.entites.Materia;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MateriaMapper {
    Materia toMateria(MateriaDto materiaDto);
    MateriaDto toMateriaDto(Materia materia);
    List<MateriaDto> toMateriaDtos(List<Materia> materias);
    void updateMateria(@MappingTarget Materia target, Materia materiaDto);
}
