package com.sergio.jwt.backend.dtos;

import lombok.*;

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
