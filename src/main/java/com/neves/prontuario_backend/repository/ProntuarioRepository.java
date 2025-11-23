package com.neves.prontuario_backend.repository;

import com.neves.prontuario_backend.domain.clinico.Prontuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProntuarioRepository extends JpaRepository<Prontuario, UUID> {

    @EntityGraph(attributePaths = {"entradas", "entradas.receituario", "entradas.profissional"})
    Optional<Prontuario> findWithEntradasByPacienteId(UUID pacienteId);
}
