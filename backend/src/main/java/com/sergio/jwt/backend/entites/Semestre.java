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
public class Semestre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nivel")
    private String nivel;

    @Column(name ="descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "semestre")
    private List<Materia>  materias  = new ArrayList<Materia>();



    public void addMateria(Materia materia) {
        materias.add(materia);
        materia.setSemestre(this);
    }

    public void removeMateria(Materia materia) {
        materias.remove(materia);
        materia.setSemestre(null);
    }
}
