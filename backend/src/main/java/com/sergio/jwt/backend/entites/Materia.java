package com.sergio.jwt.backend.entites;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table()
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "sigla")
    private String sigla;

    @Column(name = "name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "semestre_Id",
            referencedColumnName = "id"
    )
    private Semestre semestre;

    @ManyToMany
    @JoinTable(name = "materias_carrerra",
            joinColumns = @JoinColumn(name = "materia_id"),
            inverseJoinColumns = @JoinColumn(name = "carrera_id"))
    private List<Carrera> carrera_id;

    @ManyToMany
    @JoinTable(name = "grupo_mareria",
            joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_id"))
    private List<Grupo> grupoList;

    @ManyToMany(mappedBy = "materiasList")
    private List<User> userList= new ArrayList<User>();

    @OneToMany(mappedBy = "materia")
    private List<Mate_Grupo_Aula_Horario> clases_materia = new ArrayList<>();

}
