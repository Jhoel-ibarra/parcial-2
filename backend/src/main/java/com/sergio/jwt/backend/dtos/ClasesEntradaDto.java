package com.sergio.jwt.backend.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class ClasesEntradaDto {

    private long id;
    private long materiaID;
    private long grupoID;
    private long horarioID;
    private long aulaID;
    private long docenteID;

}
