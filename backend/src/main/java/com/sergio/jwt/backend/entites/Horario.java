package com.sergio.jwt.backend.entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class Horario {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "dia")
    private String dia;

    @Column(name = "HoraInicio")
    private String HoraInicio;

    @Column(name = "HoraFinal")
    private String HoraFinal;

    @OneToMany( mappedBy = "horario")
    private List<Mate_Grupo_Aula_Horario> clase_horario = new ArrayList<>();

}
