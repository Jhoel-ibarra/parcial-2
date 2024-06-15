package com.sergio.jwt.backend.dtos;


import com.sergio.jwt.backend.entites.Materia;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter

public class RequestMateria {

    private MateriaDto materiaDto;
    private SemestreDto semestreDto;

}
