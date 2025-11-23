package com.neves.prontuario_backend.repository;

import com.neves.prontuario_backend.domain.identity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {
    Optional<Token> findByValor(String valor);
}
