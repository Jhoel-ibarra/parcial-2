package com.sergio.jwt.backend.services;

import com.sergio.jwt.backend.dtos.GrupoDto;
import com.sergio.jwt.backend.entites.Aula;
import com.sergio.jwt.backend.entites.Grupo;
import com.sergio.jwt.backend.exceptions.AppException;
import com.sergio.jwt.backend.mappers.GrupoMapper;
import com.sergio.jwt.backend.repositories.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class GrupoService {

    private final GrupoRepository grupoRepository;
    private final GrupoMapper grupoMapper;

    public List<GrupoDto> getAllGrupos() {
        List<Grupo> grupos = grupoRepository.findAll();
        return grupoMapper.toGrupoDtos(grupos);
    }

    public GrupoDto getGrupo(long id) {
        Grupo grupo  = grupoRepository.findById(id)
                .orElseThrow(()->new AppException("Aula no encontrado", HttpStatus.NOT_FOUND));
        return grupoMapper.toGrupoDto(grupo);
    }

    public GrupoDto addGrupo(GrupoDto grupoDto) {
        Grupo grupo = grupoMapper.toGrupo(grupoDto);
        grupoRepository.save(grupo);
        return grupoMapper.toGrupoDto(grupo);
    }

    public GrupoDto updateGrupo(long id, GrupoDto grupoDto) {
        Grupo grupo = grupoRepository.findById(id).
                orElseThrow( ()-> new AppException("Aula no encontrado", HttpStatus.NOT_FOUND));
        grupoMapper.updateGrupo(grupo , grupoMapper.toGrupo(grupoDto));

        return grupoMapper.toGrupoDto( grupoRepository.save(grupo));
    }
    public GrupoDto deleteGrupo(long id) {
        Grupo grupo = grupoRepository.findById(id).
                orElseThrow( ()-> new AppException("Aula no encontrado", HttpStatus.NOT_FOUND));
        grupoRepository.delete(grupo);
        return grupoMapper.toGrupoDto(grupo);
    }

}
