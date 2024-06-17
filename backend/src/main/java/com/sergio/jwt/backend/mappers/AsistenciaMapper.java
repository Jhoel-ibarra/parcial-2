package com.sergio.jwt.backend.mappers;

import com.sergio.jwt.backend.dtos.AsistenciaDto;
import com.sergio.jwt.backend.dtos.SemestreDto;
import com.sergio.jwt.backend.entites.Asistencia;
import com.sergio.jwt.backend.entites.Semestre;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper(componentModel = "spring")
public interface AsistenciaMapper {
    Asistencia toAsistencia(AsistenciaDto asistenciaDto);
    AsistenciaDto toAsistenciaDto(Asistencia asistencia);
    List<AsistenciaDto> toAsistenciaDtos(List<Asistencia> asistencias);
    void updateAsistencia(@MappingTarget Asistencia asistencia, Asistencia asistenciaDto);
}
