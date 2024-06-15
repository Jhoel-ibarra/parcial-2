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
public class Grupo {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToMany(mappedBy = "grupoList")
    private List<Materia> materiasList;

    @OneToMany(mappedBy = "grupo")
    private List<Mate_Grupo_Aula_Horario> clases_grupo = new ArrayList<>();

}









