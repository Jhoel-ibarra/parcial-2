package com.sergio.jwt.backend.services;

import com.sergio.jwt.backend.dtos.MateriaDto;
import com.sergio.jwt.backend.dtos.SemestreDto;
import com.sergio.jwt.backend.entites.Materia;
import com.sergio.jwt.backend.entites.Semestre;
import com.sergio.jwt.backend.exceptions.AppException;
import com.sergio.jwt.backend.mappers.MateriaMapper;
import com.sergio.jwt.backend.repositories.MateriaRespository;
import com.sergio.jwt.backend.repositories.SemestreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MateriaService {

    private final MateriaRespository materiaRespository;
    private final MateriaMapper materiaMapper;

    private final SemestreRepository semestreRepository;

    public MateriaDto getMateria(long id) {
        Materia materia = materiaRespository.findById(id)
                .orElseThrow(() -> new AppException("Materia not found", HttpStatus.NOT_FOUND));
        return materiaMapper.toMateriaDto(materia);
    }

    public List<MateriaDto> allMateria() {
        List<Materia> all = materiaRespository.findAll();
        return materiaMapper.toMateriaDtos(all);
    }




    public MateriaDto deleteMateria(long id) {
        Materia materia = materiaRespository.findById(id)
                .orElseThrow(() -> new AppException("Materia not found", HttpStatus.NOT_FOUND));
        materiaRespository.delete(materia);
        return materiaMapper.toMateriaDto(materia);
    }

    public MateriaDto updateModulo(long id, MateriaDto materiaDto) {
        Materia materia = materiaRespository.findById(id).
                orElseThrow(() -> new AppException("Materia not found", HttpStatus.NOT_FOUND));
        materiaMapper.updateMateria(materia, materiaMapper.toMateria(materiaDto));

        Materia updaMateria = materiaRespository.save(materia);
        return materiaMapper.toMateriaDto(updaMateria);
    }

    public MateriaDto createdMateria(MateriaDto materiaDto) {

        Materia materia = materiaMapper.toMateria(materiaDto);
        Materia createMAteria = materiaRespository.save(materia);

        return materiaMapper.toMateriaDto(createMAteria);
    }

    public MateriaDto createMateriaSemestre(MateriaDto materiaDto) {
        Semestre  semestre = semestreRepository.findById(materiaDto.getSemestreId())
                .orElseThrow(() -> new AppException("Semestre not found", HttpStatus.NOT_FOUND));
        //crear la materia
        Materia materia = materiaMapper.toMateria(materiaDto);
        materia.setSemestre(semestre);
        semestre.getMaterias().add(materia);
        Materia createMAteria = materiaRespository.save(materia);
        semestreRepository.save(semestre);
        return materiaMapper.toMateriaDto(createMAteria);
    }


}
