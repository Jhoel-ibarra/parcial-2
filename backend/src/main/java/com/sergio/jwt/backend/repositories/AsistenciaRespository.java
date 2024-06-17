package com.sergio.jwt.backend.repositories;

import com.sergio.jwt.backend.entites.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsistenciaRespository extends JpaRepository<Asistencia, Long> {
}
