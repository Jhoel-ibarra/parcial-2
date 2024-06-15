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
public class Mate_Grupo_Aula_Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "materia_id" , nullable = false)
    private Materia materia;

    @ManyToOne
    @JoinColumn(name = "grupo_id" , nullable = false)
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "horario_id" , nullable = false)
    private Horario horario;

    @ManyToOne
    @JoinColumn(name = "aula_id", nullable = false)
    private Aula aula;

    @ManyToOne
    @JoinColumn(name = "docente_id", nullable = false)
    private User docente;

}
