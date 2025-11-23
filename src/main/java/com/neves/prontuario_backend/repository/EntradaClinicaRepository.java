package com.neves.prontuario_backend.repository;

import com.neves.prontuario_backend.domain.clinico.EntradaClinica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntradaClinicaRepository extends JpaRepository<EntradaClinica, UUID> {
}
