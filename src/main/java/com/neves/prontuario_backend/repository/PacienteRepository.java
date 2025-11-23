package com.neves.prontuario_backend.repository;

import com.neves.prontuario_backend.domain.identity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
}
