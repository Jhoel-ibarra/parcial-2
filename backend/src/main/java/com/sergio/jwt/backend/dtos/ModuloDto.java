package com.sergio.jwt.backend.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class ModuloDto {

    private Long id;
    @NotNull
    private String name;

}
