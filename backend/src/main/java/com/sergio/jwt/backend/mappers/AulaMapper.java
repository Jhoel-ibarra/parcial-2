package com.sergio.jwt.backend.mappers;

import com.sergio.jwt.backend.dtos.AulaDto;
import com.sergio.jwt.backend.entites.Aula;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper(componentModel = "spring")

public interface AulaMapper {
    AulaDto toAulaDto(Aula aula);
    List<AulaDto> toAulaDtos(List<Aula> aulas);
    Aula toAula(AulaDto aulaDto);
    void updateAula(@MappingTarget Aula aula, Aula aulaDto);
}
