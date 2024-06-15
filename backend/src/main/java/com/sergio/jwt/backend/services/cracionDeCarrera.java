package com.sergio.jwt.backend.services;


import com.sergio.jwt.backend.dtos.MateriaDto;
import com.sergio.jwt.backend.dtos.SemestreDto;
import com.sergio.jwt.backend.entites.Materia;
import com.sergio.jwt.backend.entites.Semestre;
import com.sergio.jwt.backend.exceptions.AppException;
import com.sergio.jwt.backend.mappers.MateriaMapper;
import com.sergio.jwt.backend.mappers.SemestreMapper;
import com.sergio.jwt.backend.repositories.MateriaRespository;
import com.sergio.jwt.backend.repositories.SemestreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class cracionDeCarrera {

    private final MateriaRespository materiaRespository;
    private final SemestreRepository semestreRepository;
    private final MateriaMapper materiaMapper;
    private final SemestreMapper semestreMapper;

    public MateriaDto postMateria(MateriaDto materiaDto, SemestreDto semestreDto) {

        Semestre semestre = semestreRepository.findById(semestreDto.getId()).
                orElseThrow(()-> new AppException( "semestre no encontrado", HttpStatus.NOT_FOUND));

        Materia materia = materiaMapper.toMateria(materiaDto);

        materia.setSemestre(semestre);

        if( semestre.getMaterias() == null){
            semestre.setMaterias(new ArrayList<>());
        }
        semestre.addMateria(materia);

        Materia saveMateria = materiaRespository.save(materia);
        log.debug("user creado ", semestreDto.getMaterias());
        return materiaMapper.toMateriaDto(saveMateria);
    }


}
