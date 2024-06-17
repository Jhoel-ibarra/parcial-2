package com.sergio.jwt.backend.repositories;

import com.sergio.jwt.backend.entites.Licencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenciaRespository extends JpaRepository<Licencia, Long> {
}
