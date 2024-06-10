package com.sergio.jwt.backend.entites;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "semestre")
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

    @OneToMany(cascade = CascadeType.ALL,
                    mappedBy = "semestre")
    private List<Materia>  materias ;


}
