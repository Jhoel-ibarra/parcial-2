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

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

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

    public LicenciaDto createLicencia(LicenciaDto licenciaDto, UserDto userDto) {
        Licencia licencia = licenciaMapper.toLicencia(licenciaDto);
        User user = this.getUser(userDto);
        licencia.setDocente(user);

        ZoneId boliviaZoneId = ZoneId.of("America/La_Paz");
        licencia.setCreado(ZonedDateTime.now(boliviaZoneId).toLocalDateTime());

           List<Mate_Grupo_Aula_Horario> clases = user.getCargaHorariaList();
            if(clases.isEmpty()){
                throw new AppException("carga horaria list empty", HttpStatus.BAD_REQUEST);
            }

           for ( Mate_Grupo_Aula_Horario clase : clases){
               LocalTime horaInicio = licencia.getCreado().plusHours(1).toLocalTime();
               LocalTime horafinal = clase.getHorario().getHoraInicio();
               if(horaInicio.isBefore(horafinal) ||
                       horaInicio.equals(horafinal) ){
                     throw  new AppException("la hora para perdir licencia esta fuera de rango  fuera de rango", HttpStatus.BAD_REQUEST);
               }
           }

        licencia.setCreado(LocalDateTime.now());
        user.getLicencias().add(licencia);
        userRepository.save(user);
        return licenciaMapper.toLicenciaDto(licenciaRespository.save(licencia));
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
