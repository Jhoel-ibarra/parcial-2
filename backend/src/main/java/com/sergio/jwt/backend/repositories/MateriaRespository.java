package com.sergio.jwt.backend.repositories;

import com.sergio.jwt.backend.entites.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRespository extends JpaRepository<Materia, Long> {
}
