package com.sergio.jwt.backend.mappers;

import com.sergio.jwt.backend.dtos.GrupoDto;
import com.sergio.jwt.backend.entites.Grupo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper(componentModel = "spring")
public interface GrupoMapper {

    Grupo toGrupo(GrupoDto grupoDto);
    GrupoDto toGrupoDto(Grupo grupo);
    List<GrupoDto> toGrupoDtos(List<Grupo> grupos);
    void updateGrupo( @MappingTarget Grupo targe ,Grupo grupo);

}
