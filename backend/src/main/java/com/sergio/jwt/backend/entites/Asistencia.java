package com.sergio.jwt.backend.entites;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "numero")
    private long numero;

    @Column(name = "estado")
    private String estado;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha")
    private String fecha;


}
