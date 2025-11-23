package com.neves.prontuario_backend.repository;

import com.neves.prontuario_backend.domain.clinico.Receituario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReceituarioRepository extends JpaRepository<Receituario, UUID> {
}
