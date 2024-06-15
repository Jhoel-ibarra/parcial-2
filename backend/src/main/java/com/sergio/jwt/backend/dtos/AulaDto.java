package com.sergio.jwt.backend.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class AulaDto {

    private Long id;
    private Long numero;

}
