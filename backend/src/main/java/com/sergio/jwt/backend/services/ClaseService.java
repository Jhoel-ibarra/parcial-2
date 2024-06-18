package com.sergio.jwt.backend.services;

import com.sergio.jwt.backend.dtos.ClasesDto;
import com.sergio.jwt.backend.dtos.ClasesEntradaDto;
import com.sergio.jwt.backend.dtos.ReporteEntrada;
import com.sergio.jwt.backend.dtos.ReporteSalida;
import com.sergio.jwt.backend.entites.*;
import com.sergio.jwt.backend.exceptions.AppException;
import com.sergio.jwt.backend.mappers.ClasesMapper;
import com.sergio.jwt.backend.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<ClasesDto> myHorario(long id){
        User docente = userRepository.findById(id)
                .orElseThrow( () -> new AppException("usuario no encontrado" , HttpStatus.NOT_FOUND));
        List<Mate_Grupo_Aula_Horario> clases = docente.getCargaHorariaList();
        return clasesMapper.toClasesDtos(clases);
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

        Mate_Grupo_Aula_Horario clases = new Mate_Grupo_Aula_Horario();
        clases.setMateria(materia);
        clases.setGrupo(grupo);
        clases.setHorario(horario);
        clases.setAula(aula);
        clases.setDocente(user);

        Mate_Grupo_Aula_Horario nuevo = clasesRepository.save(clases);

        Asistencia asistencia =  new Asistencia();
        asistencia.setEstado("falta");
        asistencia.setClase(nuevo);

        asistenciaRespository.save(asistencia);

        nuevo.setAsistencia(asistencia);
        clasesRepository.save(nuevo);

        materia.getClases_materia().add(nuevo);
        grupo.getClases_grupo().add(nuevo);
        horario.getClase_horario().add(nuevo);
        aula.getClases_aula().add(nuevo);
        user.getCargaHorariaList().add(nuevo);

        return clasesMapper.toClasesDto(nuevo);
    }

    public List<ReporteSalida> reporteSalida(ReporteEntrada reporteEntrada){
        List<Mate_Grupo_Aula_Horario> clases = new LinkedList<>();
       if(reporteEntrada.getUser_id() == 0){
           clases = clasesRepository.findAll();
       }else {
           User docente = userRepository.findById(reporteEntrada.getUser_id())
                   .orElseThrow( () -> new AppException("usuario no encontrado" , HttpStatus.NOT_FOUND));
            clases = docente.getCargaHorariaList();
       }
       if (clases.isEmpty()){
           new AppException("No se encuentra clases existente", HttpStatus.NOT_FOUND);
       }

        if(reporteEntrada.getAsistencia_estado() != null ){
            List<Mate_Grupo_Aula_Horario> clasesConFalta = clases.stream()
                    .filter(clase -> clase.getAsistencia().getEstado().equals( reporteEntrada.getAsistencia_estado() ))
                    .collect(Collectors.toList());
            clases = clasesConFalta;
        }
       if(reporteEntrada.getMateria() != null ){
           List<Mate_Grupo_Aula_Horario> clasesConFalta = clases.stream()
                   .filter(clase -> clase.getMateria().getName().equals( reporteEntrada.getMateria() ))
                   .collect(Collectors.toList());
           clases = clasesConFalta;
       }
        if(reporteEntrada.getGrupo() != null ){
            List<Mate_Grupo_Aula_Horario> clasesConFalta = clases.stream()
                    .filter(clase -> clase.getGrupo().getNombre().equals(reporteEntrada.getGrupo() ))
                    .collect(Collectors.toList());
            clases = clasesConFalta;
        }

        if(reporteEntrada.getFecha_inicio() != null ){
            List<Mate_Grupo_Aula_Horario> clasesConFalta = clases.stream()
                    .filter(clase -> clase.getAsistencia().getFecha().isAfter(reporteEntrada.getFecha_inicio()) )
                    .collect(Collectors.toList());
            clases = clasesConFalta;
        }
        if(reporteEntrada.getFecha_fin() != null ){
            List<Mate_Grupo_Aula_Horario> clasesConFalta = clases.stream()
                    .filter(clase -> clase.getAsistencia().getFecha().isBefore(reporteEntrada.getFecha_fin()) )
                    .collect(Collectors.toList());
            clases = clasesConFalta;
        }

        List<ReporteSalida> reporteSalida = new LinkedList<>();
        for(Mate_Grupo_Aula_Horario clase : clases){
            ReporteSalida dto = new ReporteSalida();
            dto.setNombre(clase.getDocente().getFirstName()+" "+clase.getDocente().getLastName());
            dto.setMateria(clase.getMateria().getName());
            dto.setGrupo(clase.getGrupo().getNombre());
            dto.setAsistencia(clase.getAsistencia().getEstado());
            dto.setFecha(clase.getAsistencia().getFecha());
            reporteSalida.add(dto);
        }
        return reporteSalida;
    }



}
