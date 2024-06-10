package com.sergio.jwt.backend.mappers;


import com.sergio.jwt.backend.dtos.ModuloDto;
import com.sergio.jwt.backend.dtos.SemestreDto;
import com.sergio.jwt.backend.entites.Modulo;
import com.sergio.jwt.backend.entites.Semestre;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SemestreMapper {

    Semestre toSemestre(SemestreDto semestreDto);
    SemestreDto toSemestreDto(Semestre semestre);
    List<SemestreDto> toSemestresDtos(List<Semestre> semestres);

    void updateSemestre(@MappingTarget Semestre target, Semestre semestreDto);


}

