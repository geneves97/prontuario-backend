package com.neves.prontuario_backend.repository;

import com.neves.prontuario_backend.domain.identity.ProfissionalDaSaude;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfissionalRepository extends JpaRepository<ProfissionalDaSaude, UUID> {
}
