package com.sergio.jwt.backend.repositories;

import com.sergio.jwt.backend.entites.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}
