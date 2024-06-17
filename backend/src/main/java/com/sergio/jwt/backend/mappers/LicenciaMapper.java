package com.sergio.jwt.backend.mappers;


import com.sergio.jwt.backend.dtos.LicenciaDto;
import com.sergio.jwt.backend.dtos.MateriaDto;
import com.sergio.jwt.backend.entites.Licencia;
import com.sergio.jwt.backend.entites.Materia;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LicenciaMapper {

    LicenciaDto toLicenciaDto(Licencia licencia);
    Licencia toLicencia(LicenciaDto licenciaDto);
    List<LicenciaDto> toLicenciaDtos(List<Licencia> licencias);
    void updateLicenciaDto(@MappingTarget Licencia licencia, Licencia licenciaDto);

}

