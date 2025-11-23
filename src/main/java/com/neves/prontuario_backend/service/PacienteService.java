package com.neves.prontuario_backend.service;

import com.neves.prontuario_backend.api.dto.PacienteAtualizacaoRequest;
import com.neves.prontuario_backend.api.dto.PacienteCadastroRequest;
import com.neves.prontuario_backend.domain.clinico.Prontuario;
import com.neves.prontuario_backend.domain.identity.Credencial;
import com.neves.prontuario_backend.domain.identity.Paciente;
import com.neves.prontuario_backend.domain.identity.Perfil;
import com.neves.prontuario_backend.repository.PacienteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PasswordEncoder passwordEncoder;

    public PacienteService(PacienteRepository pacienteRepository, PasswordEncoder passwordEncoder) {
        this.pacienteRepository = pacienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Paciente registrarPaciente(PacienteCadastroRequest request) {
        Paciente paciente = new Paciente();
        paciente.setNome(request.getNome());
        paciente.setEmail(request.getEmail());
        paciente.setContato(request.getContato());
        paciente.setDataNascimento(request.getDataNascimento());
        paciente.setPerfis(Set.of(Perfil.PACIENTE));

        Credencial credencial = Credencial.builder()
                .username(request.getEmail())
                .senhaHash(passwordEncoder.encode(request.getSenha()))
                .build();
        credencial.setUsuario(paciente);
        paciente.setCredencial(credencial);

        Prontuario prontuario = Prontuario.builder()
                .historicoSocial("Sem histórico inicial")
                .build();
        paciente.vincularProntuario(prontuario);

        return pacienteRepository.save(paciente);
    }

    @Transactional
    public Paciente atualizarPaciente(UUID pacienteId, PacienteAtualizacaoRequest request) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));
        paciente.setNome(request.getNome());
        paciente.setContato(request.getContato());
        paciente.setDataNascimento(request.getDataNascimento());
        return paciente;
    }

    public Paciente buscarPaciente(UUID pacienteId) {
        return pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));
    }
}
