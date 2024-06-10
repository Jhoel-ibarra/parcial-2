package com.sergio.jwt.backend.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class MateriaDto {

    private Long id;

    @NotNull
    private String sigla;
    @NotNull
    private String name;

    private Long semestreId;

}
