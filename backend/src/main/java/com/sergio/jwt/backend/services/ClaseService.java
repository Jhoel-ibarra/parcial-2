package com.sergio.jwt.backend.services;

import com.sergio.jwt.backend.dtos.ClasesDto;
import com.sergio.jwt.backend.dtos.ClasesEntradaDto;
import com.sergio.jwt.backend.entites.*;
import com.sergio.jwt.backend.exceptions.AppException;
import com.sergio.jwt.backend.mappers.ClasesMapper;
import com.sergio.jwt.backend.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaseService {

    private final ClasesMapper clasesMapper;
    private final ClasesRepository clasesRepository;

    private final AulaRepository aulaRepository;
    private final HorarioRepository horarioRepository;
    private final MateriaRespository materiaRespository;
    private final GrupoRepository grupoRepository;
    private final UserRepository userRepository;
    private final AsistenciaService asistenciaService;
    private final AsistenciaRespository asistenciaRespository;

    public List<ClasesDto> allClases(){
        List<Mate_Grupo_Aula_Horario> clases = clasesRepository.findAll();
        return clasesMapper.toClasesDtos(clases);
    }



    public ClasesDto getClaseId(long id){
        Mate_Grupo_Aula_Horario clases = clasesRepository.findById(id)
                .orElseThrow(()-> new AppException("clases no encontrao" , HttpStatus.NOT_FOUND));
        return clasesMapper.toClasesDto(clases);
    }
    @Transactional
    public ClasesDto nuevoClase(ClasesEntradaDto clasesDto){

        Materia materia = materiaRespository.findById(clasesDto.getMateriaID())
                .orElseThrow( ()-> new AppException("No se encontro la materia", HttpStatus.NOT_FOUND));

        Grupo grupo = grupoRepository.findById(clasesDto.getGrupoID())
                .orElseThrow( ()-> new AppException("No se encontro la grupo", HttpStatus.NOT_FOUND));

        Horario horario = horarioRepository.findById(clasesDto.getHorarioID())
                .orElseThrow( ()-> new AppException("No se encontro la hoario", HttpStatus.NOT_FOUND));

        Aula aula = aulaRepository.findById(clasesDto.getAulaID())
                .orElseThrow( ()-> new AppException("No se encontro la aula", HttpStatus.NOT_FOUND));

        User user = userRepository.findById(clasesDto.getDocenteID())
                .orElseThrow( ()-> new AppException("No se encontro la usuario", HttpStatus.NOT_FOUND));
        Asistencia asistencia =  new Asistencia();

        Mate_Grupo_Aula_Horario clases = new Mate_Grupo_Aula_Horario();

        asistencia.setEstado("falta");
        clases.setAsistencia(asistencia);

        clases.setMateria(materia);
        materia.getClases_materia().add(clases);

        clases.setGrupo(grupo);
        grupo.getClases_grupo().add(clases);

        clases.setHorario(horario);
        horario.getClase_horario().add(clases);

        clases.setAula(aula);
        aula.getClases_aula().add(clases);

        clases.setDocente(user);
        user.getCargaHorariaList().add(clases);


        user.getMateriasList().add(materia);
        materia.getUserList().add(user);

        //clasesMapper.updateClases(clases, clases);
        Mate_Grupo_Aula_Horario nuevo = clasesRepository.save(clases);
        return clasesMapper.toClasesDto(nuevo);
    }
}
