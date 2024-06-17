package com.sergio.jwt.backend.services;

import com.sergio.jwt.backend.dtos.AsistenciaDto;
import com.sergio.jwt.backend.dtos.SemestreDto;
import com.sergio.jwt.backend.entites.Asistencia;
import com.sergio.jwt.backend.entites.Semestre;
import com.sergio.jwt.backend.exceptions.AppException;
import com.sergio.jwt.backend.mappers.AsistenciaMapper;
import com.sergio.jwt.backend.repositories.AsistenciaRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AsistenciaService {

    private final AsistenciaRespository asistenciaRespository;
    private final AsistenciaMapper asistenciaMapper;

    public List<AsistenciaDto> allAsistencias() {
        List<Asistencia> asistencias = asistenciaRespository.findAll();
        return asistenciaMapper.toAsistenciaDtos(asistencias);
    }

    public AsistenciaDto getAsistencia(long id) {
        Asistencia asistencia = asistenciaRespository.findById(id)
                .orElseThrow( ()-> new AppException("Asistencia no encontrado", HttpStatus.NOT_FOUND) );
        return asistenciaMapper.toAsistenciaDto(asistencia);
    }

    public AsistenciaDto createAsistencia(long id ,  AsistenciaDto asistenciaDto) {
        Asistencia asistencia = asistenciaMapper.toAsistencia(asistenciaDto);
        Asistencia savedAsistencia = asistenciaRespository.save(asistencia);
        return asistenciaMapper.toAsistenciaDto(savedAsistencia);
    }

    public AsistenciaDto deleteAsistencia(long id) {
        Asistencia asistencia = asistenciaRespository.findById(id)
                .orElseThrow( ()-> new AppException("Asistencia no encontrado", HttpStatus.NOT_FOUND) );
        asistenciaRespository.delete(asistencia);
        return asistenciaMapper.toAsistenciaDto(asistencia);
    }

    public AsistenciaDto updateAsistencia( Long id , AsistenciaDto asistenciaDto) {
        Asistencia asistencia = asistenciaRespository.findById(id)
                .orElseThrow( ()-> new AppException("Asistencia no encontrado", HttpStatus.NOT_FOUND) );
        asistenciaMapper.updateAsistencia(asistencia, asistenciaMapper.toAsistencia(asistenciaDto));

        Asistencia savedAsistencia = asistenciaRespository.save(asistencia);
        return asistenciaMapper.toAsistenciaDto(savedAsistencia);
    }

    public




}
