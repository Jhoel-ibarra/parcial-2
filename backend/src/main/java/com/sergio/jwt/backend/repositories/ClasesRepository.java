package com.sergio.jwt.backend.repositories;

import com.sergio.jwt.backend.entites.Mate_Grupo_Aula_Horario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasesRepository extends JpaRepository<Mate_Grupo_Aula_Horario, Long> {
}
