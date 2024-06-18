package com.sergio.jwt.backend.mappers;

import com.sergio.jwt.backend.dtos.HorarioDto;
import com.sergio.jwt.backend.entites.Horario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


import java.util.List;
@Mapper(componentModel = "spring")
public interface HorarioMapper {
    Horario toHorario( HorarioDto horarioDto );
    HorarioDto toHorarioDto( Horario horario );
    List<HorarioDto> toHorarioDtos( List<Horario> horarios );
    void updateHorario(@MappingTarget Horario horario, Horario horarioDto );
}

