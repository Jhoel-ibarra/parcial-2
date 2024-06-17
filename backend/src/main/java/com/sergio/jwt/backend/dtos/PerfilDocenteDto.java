package com.sergio.jwt.backend.dtos;

import com.sergio.jwt.backend.entites.Mate_Grupo_Aula_Horario;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class PerfilDocenteDto {

    private String firstName;
    private String lastName;
    private String login;

}
