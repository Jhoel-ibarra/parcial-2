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

public class CargaHoraria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;



}
