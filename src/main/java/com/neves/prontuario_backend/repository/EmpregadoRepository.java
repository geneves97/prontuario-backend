package com.neves.prontuario_backend.repository;

import com.neves.prontuario_backend.domain.identity.Empregado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmpregadoRepository extends JpaRepository<Empregado, UUID> {
}
