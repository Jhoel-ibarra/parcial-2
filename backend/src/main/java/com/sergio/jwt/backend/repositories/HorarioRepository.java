package com.sergio.jwt.backend.repositories;

import com.sergio.jwt.backend.entites.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
}
