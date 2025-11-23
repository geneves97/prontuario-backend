package com.neves.prontuario_backend.repository;

import com.neves.prontuario_backend.domain.identity.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CredencialRepository extends JpaRepository<Credencial, UUID> {
    Optional<Credencial> findByUsername(String username);
}
