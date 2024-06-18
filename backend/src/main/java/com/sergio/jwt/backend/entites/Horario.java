package com.sergio.jwt.backend.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "dia")
    private String dia;

    @Column(name = "inicia")
    private LocalTime inicia;

    @Column(name = "termina")
    private LocalTime termina;


    @OneToMany( mappedBy = "horario")
    private List<Mate_Grupo_Aula_Horario> clase_horario = new ArrayList<>();

}
