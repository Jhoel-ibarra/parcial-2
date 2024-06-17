package com.sergio.jwt.backend.dtos;

import com.sergio.jwt.backend.entites.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter

public class LicenciaDto {
    private Long id;

    private String urlfoto;
    private String estado;
    private String descripcion;
    private LocalDate fecha;
    private LocalTime hora;


}
