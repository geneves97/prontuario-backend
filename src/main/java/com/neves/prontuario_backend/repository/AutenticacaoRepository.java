package com.neves.prontuario_backend.repository;

import com.neves.prontuario_backend.entity.AutenticacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutenticacaoRepository extends JpaRepository<AutenticacaoEntity, Integer> {
    Optional<AutenticacaoEntity> findByUsername(String username);
    Optional<AutenticacaoEntity> findByToken(String token);
}
