package com.sergio.jwt.backend.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class ModuloDto {

    private Long id;
    private String name;
    private List<AulaDto> aulas;
}

