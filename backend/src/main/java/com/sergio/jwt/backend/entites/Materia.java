package com.sergio.jwt.backend.entites;


import jakarta.persistence.*;
import lombok.*;

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

    @Column(name ="sigla")
    private String sigla;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name  = "semestreId" , referencedColumnName = "id")
    private Semestre semestre;

}
