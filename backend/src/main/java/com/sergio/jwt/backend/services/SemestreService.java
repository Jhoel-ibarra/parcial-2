package com.sergio.jwt.backend.services;

import com.sergio.jwt.backend.dtos.ModuloDto;
import com.sergio.jwt.backend.dtos.SemestreDto;
import com.sergio.jwt.backend.entites.Modulo;
import com.sergio.jwt.backend.entites.Semestre;
import com.sergio.jwt.backend.exceptions.AppException;
import com.sergio.jwt.backend.mappers.SemestreMapper;
import com.sergio.jwt.backend.repositories.SemestreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SemestreService {

    private final SemestreRepository semestreRepository;
    private final SemestreMapper semestreMapper;

    public List<SemestreDto> allSemestres() {
        List<Semestre> all = semestreRepository.findAll();
        return semestreMapper.toSemestresDtos(all);
    }

    public SemestreDto getSemestre(long id) {
        Semestre semestre = semestreRepository.findById(id)
                .orElseThrow(() -> new AppException("semestre not found", HttpStatus.NOT_FOUND));
        return semestreMapper.toSemestreDto(semestre);
    }

    public SemestreDto createSemestre(SemestreDto semestreDto) {
        Semestre semestre = semestreMapper.toSemestre(semestreDto);
        Semestre savedSemestre = semestreRepository.save(semestre);
        return semestreMapper.toSemestreDto(savedSemestre);
    }

    public SemestreDto deleteSemestre(Long id) {
        Semestre semestre = semestreRepository.findById(id)
                .orElseThrow(() -> new AppException("semestre not found", HttpStatus.NOT_FOUND));
        semestreRepository.delete(semestre);
        return semestreMapper.toSemestreDto(semestre);
    }

    public SemestreDto updateSemestre(Long id, SemestreDto semestreDto) {
        Semestre semestre = semestreRepository.findById(id)
                .orElseThrow(() -> new AppException("semestre not found", HttpStatus.NOT_FOUND));
        semestreMapper.updateSemestre(semestre , semestreMapper.toSemestre(semestreDto));

        Semestre savedSemestre = semestreRepository.save(semestre);
        return semestreMapper.toSemestreDto(savedSemestre);
    }

}
