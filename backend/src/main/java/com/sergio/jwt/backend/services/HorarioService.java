package com.sergio.jwt.backend.services;


import com.sergio.jwt.backend.dtos.AulaDto;
import com.sergio.jwt.backend.dtos.HorarioDto;
import com.sergio.jwt.backend.entites.Aula;
import com.sergio.jwt.backend.entites.Horario;
import com.sergio.jwt.backend.exceptions.AppException;
import com.sergio.jwt.backend.mappers.HorarioMapper;
import com.sergio.jwt.backend.mappers.MateriaMapper;
import com.sergio.jwt.backend.repositories.HorarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioService {

    private final HorarioRepository horarioRepository;
    private  final HorarioMapper horarioMapper;


    public List<HorarioDto> findAll() {
        List<Horario> horarios = horarioRepository.findAll();
        return horarioMapper.toHorarioDtos(horarios);
    }

    public HorarioDto getHorario(Long id) {
        Horario horario = horarioRepository.findById(id).
                orElseThrow(()-> new AppException("horaio no encontrado", HttpStatus.NOT_FOUND));

        return horarioMapper.toHorarioDto(horario);
    }

    public HorarioDto addHorario(HorarioDto horarioDto) {
        Horario horario = horarioMapper.toHorario(horarioDto);
        horario = horarioRepository.save(horario);
        return horarioMapper.toHorarioDto(horario);
    }
    public HorarioDto updateHorario(Long id, HorarioDto horarioDto) {
        Horario horario = horarioRepository.findById(id).
                orElseThrow(()-> new AppException("horaio no encontrado", HttpStatus.NOT_FOUND));

        horarioMapper.updateHorario(horario, horarioMapper.toHorario(horarioDto));
        horario = horarioRepository.save(horario);
        return horarioMapper.toHorarioDto(horario);
    }

    public HorarioDto deleteHorario(Long id) {
        Horario horario = horarioRepository.findById(id).
                orElseThrow(()-> new AppException("horaio no encontrado", HttpStatus.NOT_FOUND));
        horarioRepository.delete(horario);
        return horarioMapper.toHorarioDto(horario);
    }
}
