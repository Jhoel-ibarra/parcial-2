package com.sergio.jwt.backend.services;

import com.sergio.jwt.backend.dtos.LicenciaDto;
import com.sergio.jwt.backend.dtos.UserDto;
import com.sergio.jwt.backend.entites.Licencia;
import com.sergio.jwt.backend.entites.Mate_Grupo_Aula_Horario;
import com.sergio.jwt.backend.entites.User;
import com.sergio.jwt.backend.exceptions.AppException;
import com.sergio.jwt.backend.mappers.LicenciaMapper;
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
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LicenciaService {

    private final LicenciaMapper licenciaMapper;
    private final LicenciaRespository licenciaRespository;

    private final UserRepository userRepository;

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
    @Transactional
    public LicenciaDto createLicencia(LicenciaDto licenciaDto, UserDto userDto) {
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

        if(licencia.getFecha().isEqual(ZonedDateTime.now(boliviaZoneId).toLocalDate())){
                for ( Mate_Grupo_Aula_Horario clase : clases){
                    if (localDateTimeNow.getDayOfWeek().
                            getDisplayName(TextStyle.FULL,Locale.forLanguageTag("es")).
                            equals(clase.getHorario().getDia())){
                    LocalTime horaInicio = licencia.getCreado().plusHours(1).toLocalTime();
                    LocalTime horafinal = clase.getHorario().getHoraInicio();
                    if(horaInicio.isBefore(horafinal) ||
                            horaInicio.equals(horafinal) ){
                        throw  new AppException("la hora para perdir licencia esta fuera de rango  fuera de rango", HttpStatus.BAD_REQUEST);
                     }
                    }
                }
        }

        Licencia licenciaSave = licenciaRespository.save(licencia);
        user.getLicencias().add(licenciaSave);
        userRepository.save(user);
        return licenciaMapper.toLicenciaDto(licenciaSave);
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



}
