package com.sergio.jwt.backend.dtos;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class GrupoDto {

    private long id;
    private String nombre;
    public List<MateriaDto> materiasList;

}
