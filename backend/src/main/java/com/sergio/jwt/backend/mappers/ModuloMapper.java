package com.sergio.jwt.backend.mappers;

import com.sergio.jwt.backend.dtos.ModuloDto;
import com.sergio.jwt.backend.entites.Modulo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModuloMapper {

    Modulo toModulo(ModuloDto moduloDto);
    ModuloDto toModuloDto(Modulo modulo);
    List<ModuloDto> toModuloDtos(List<Modulo> modulos);

    void updateModulo(@MappingTarget Modulo target, Modulo moduloDto);
}
