package com.sergio.jwt.backend.dtos;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class SemestreDto {
    private Long id;
    private String nivel;
    private String descripcion;

    private List<MateriaDto> materias;
}
