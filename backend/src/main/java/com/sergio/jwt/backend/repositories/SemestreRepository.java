package com.sergio.jwt.backend.repositories;

import com.sergio.jwt.backend.entites.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemestreRepository  extends JpaRepository<Semestre, Long> {
}
