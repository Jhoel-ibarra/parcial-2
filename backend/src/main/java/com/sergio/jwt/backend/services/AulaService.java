package com.sergio.jwt.backend.services;


import com.sergio.jwt.backend.dtos.AulaDto;
import com.sergio.jwt.backend.dtos.SemestreDto;
import com.sergio.jwt.backend.entites.Aula;
import com.sergio.jwt.backend.entites.Semestre;
import com.sergio.jwt.backend.exceptions.AppException;
import com.sergio.jwt.backend.mappers.AulaMapper;
import com.sergio.jwt.backend.repositories.AulaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AulaService {

    private final AulaRepository aulaRepository;
    private final AulaMapper aulaMapper;

    public List<AulaDto> getAllAulas() {
        List<Aula> aulas = aulaRepository.findAll();
        return aulaMapper.toAulaDtos(aulas);
    }

    public AulaDto getAula(long id) {
        Aula aula = aulaRepository.findById(id).
                orElseThrow(()-> new AppException("Aula not found" , HttpStatus.NOT_FOUND));
        return aulaMapper.toAulaDto(aula);
    }

    public AulaDto createAula(AulaDto aulaDto) {
        Aula aula = aulaMapper.toAula(aulaDto);
        Aula nuevaAula =  aulaRepository.save(aula);
        return aulaMapper.toAulaDto(nuevaAula);
    }


    public AulaDto DeleteAula(long id) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow( ()-> new AppException("Aula not found" , HttpStatus.NOT_FOUND));
        aulaRepository.delete(aula);
        return aulaMapper.toAulaDto(aula);
    }

    public AulaDto updateAula(long id, AulaDto aulaDto) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new AppException("Aula not found" , HttpStatus.NOT_FOUND));
        aulaMapper.updateAula(aula , aulaMapper.toAula(aulaDto));
        Aula nuevo  = aulaRepository.save(aula);
        return aulaMapper.toAulaDto(nuevo);
    }

}
