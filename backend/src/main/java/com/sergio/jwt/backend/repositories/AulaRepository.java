package com.sergio.jwt.backend.repositories;

import com.sergio.jwt.backend.entites.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AulaRepository extends JpaRepository<Aula, Long> {
}
