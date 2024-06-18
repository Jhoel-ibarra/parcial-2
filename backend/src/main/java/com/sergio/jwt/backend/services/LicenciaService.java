package com.sergio.jwt.backend.services;

import com.sergio.jwt.backend.dtos.LicenciaDto;
import com.sergio.jwt.backend.dtos.UserDto;
import com.sergio.jwt.backend.entites.Asistencia;
import com.sergio.jwt.backend.entites.Licencia;
import com.sergio.jwt.backend.entites.Mate_Grupo_Aula_Horario;
import com.sergio.jwt.backend.entites.User;
import com.sergio.jwt.backend.exceptions.AppException;
import com.sergio.jwt.backend.mappers.LicenciaMapper;
import com.sergio.jwt.backend.repositories.AsistenciaRespository;
import com.sergio.jwt.backend.repositories.ClasesRepository;
import com.sergio.jwt.backend.repositories.LicenciaRespository;
import com.sergio.jwt.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LicenciaService {

    private final LicenciaMapper licenciaMapper;
    private final LicenciaRespository licenciaRespository;
    private final AsistenciaRespository asistenciaRespository;
    private final ClasesRepository ClasesRepository;

    private final UserRepository userRepository;
    private final ClasesRepository clasesRepository;

    public LicenciaDto getLicencia(long id) {
        Licencia licencia = licenciaRespository.findById(id)
                .orElseThrow(() -> new AppException("licencia not found", HttpStatus.NOT_FOUND));
        return licenciaMapper.toLicenciaDto(licencia);
    }

    public List<LicenciaDto> getAll(UserDto userDto) {
        User user = getUser(userDto);
        List<Licencia> licencias = user.getLicencias();
        return licenciaMapper.toLicenciaDtos(licencias);
    }


    public LicenciaDto deleteLicencia(long id) {
        Licencia licencia = licenciaRespository.findById(id)
                .orElseThrow( () -> new AppException("licencia not found", HttpStatus.NOT_FOUND));
        licenciaRespository.delete(licencia);
        return licenciaMapper.toLicenciaDto(licencia);
    }

    public LicenciaDto updateLicencia(long id, LicenciaDto licenciaDto) {
        Licencia licencia  =licenciaRespository.findById(id)
                .orElseThrow( () -> new AppException("licencia not found", HttpStatus.NOT_FOUND));

        licenciaMapper.updateLicenciaDto(licencia, licenciaMapper.toLicencia(licenciaDto));
        Licencia updatedLicencia = licenciaRespository.save(licencia);
        return licenciaMapper.toLicenciaDto(updatedLicencia);
    }


    public User getUser(UserDto userDto){
        return userRepository.findById(userDto.getId())
                .orElseThrow(() -> new AppException("usuario no enontrado", HttpStatus.NOT_FOUND));
    }


    public List<LicenciaDto> allDocente(long id) {
        User docente = userRepository.findById(id)
                .orElseThrow(() -> new AppException("usuario no enontrado", HttpStatus.NOT_FOUND));
        List<Licencia> licencias = docente.getLicencias();
        return licenciaMapper.toLicenciaDtos(licencias);
    }
    @Transactional
    public LicenciaDto createLicencia(LicenciaDto licenciaDto, long id) {
        Licencia licencia = licenciaMapper.toLicencia(licenciaDto);

        ZoneId boliviaZoneId = ZoneId.of("America/La_Paz");
        LocalDateTime localDateTimeNow = ZonedDateTime.now(boliviaZoneId).toLocalDateTime();
        licencia.setCreado(localDateTimeNow);

        Mate_Grupo_Aula_Horario clase = clasesRepository.findById(id)
                .orElseThrow( () -> new AppException("clase no enontrado", HttpStatus.NOT_FOUND));

        if(licencia.getFecha().isBefore(ZonedDateTime.now(boliviaZoneId).toLocalDate())){
            throw new AppException("fecha incorrecta", HttpStatus.BAD_REQUEST);
        }

        LocalTime horaInicio = licencia.getCreado().plusMinutes(30).toLocalTime();//hora actual
        LocalTime horafinal = clase.getHorario().getInicia();//hora de licencia
        boolean ban = false;
        if(horaInicio.isBefore(horafinal) || horaInicio.equals(horafinal) ){
                ban = true;
                Asistencia asistencia = clase.getAsistencia();
                asistencia.setEstado("licencia");
                asistenciaRespository.save(asistencia);
        }

        if(!ban){
            throw  new AppException("fuera de rango  para la de licencia o ese dia no tiene clases", HttpStatus.BAD_REQUEST);
        }
        User user = clase.getDocente();

        Licencia licenciaSave = licenciaRespository.save(licencia);
        user.getLicencias().add(licenciaSave);
        userRepository.save(user);
        return licenciaMapper.toLicenciaDto(licenciaSave);
    }



    @Transactional
    public LicenciaDto createLicencia0(LicenciaDto licenciaDto, UserDto userDto) {

        Licencia licencia = licenciaMapper.toLicencia(licenciaDto);
        User user = this.getUser(userDto);
        licencia.setDocente(user);

        ZoneId boliviaZoneId = ZoneId.of("America/La_Paz");
        LocalDateTime localDateTimeNow = ZonedDateTime.now(boliviaZoneId).toLocalDateTime();
        licencia.setCreado(localDateTimeNow);

        List<Mate_Grupo_Aula_Horario> clases = user.getCargaHorariaList();
        if(clases.isEmpty()){
            throw new AppException("carga horaria list empty", HttpStatus.BAD_REQUEST);
        }
        if(licencia.getFecha().isBefore(ZonedDateTime.now(boliviaZoneId).toLocalDate())){
            throw new AppException("fecha incorrecta", HttpStatus.BAD_REQUEST);
        }
        boolean horarioEncontrado = false;

        for ( Mate_Grupo_Aula_Horario clase : clases){
            if (licencia.getFecha().getDayOfWeek().
                    getDisplayName(TextStyle.FULL,Locale.forLanguageTag("es")).
                    equals(clase.getHorario().getDia())){
                LocalTime horaInicio = licencia.getCreado().plusMinutes(30).toLocalTime();//hora actual
                LocalTime horafinal = clase.getHorario().getInicia();//hora de licencia
                if(horaInicio.isBefore(horafinal) ||
                        horaInicio.equals(horafinal) ){
                    if(licencia.getHora().equals(horafinal)){
                        Asistencia asistencia = clase.getAsistencia();
                        asistencia.setEstado("asistencia");
                        asistenciaRespository.save(asistencia);
                        horarioEncontrado =true;
                        break;
                    }

                }

            }
        }
        if(!horarioEncontrado){
            throw  new AppException("fuera de rango  para la de licencia o ese dia no tiene clases", HttpStatus.BAD_REQUEST);
        }
        Licencia licenciaSave = licenciaRespository.save(licencia);
        user.getLicencias().add(licenciaSave);
        userRepository.save(user);
        return licenciaMapper.toLicenciaDto(licenciaSave);
    }
}
