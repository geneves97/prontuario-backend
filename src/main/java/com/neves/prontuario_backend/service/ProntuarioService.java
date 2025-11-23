package com.neves.prontuario_backend.service;

import com.neves.prontuario_backend.api.dto.EntradaClinicaRequest;
import com.neves.prontuario_backend.domain.clinico.EntradaClinica;
import com.neves.prontuario_backend.domain.clinico.Prontuario;
import com.neves.prontuario_backend.domain.identity.Paciente;
import com.neves.prontuario_backend.domain.identity.ProfissionalDaSaude;
import com.neves.prontuario_backend.repository.EntradaClinicaRepository;
import com.neves.prontuario_backend.repository.PacienteRepository;
import com.neves.prontuario_backend.repository.ProfissionalRepository;
import com.neves.prontuario_backend.repository.ProntuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProntuarioService {

    private final PacienteRepository pacienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ProntuarioRepository prontuarioRepository;
    private final EntradaClinicaRepository entradaClinicaRepository;

    public ProntuarioService(PacienteRepository pacienteRepository,
                             ProfissionalRepository profissionalRepository,
                             ProntuarioRepository prontuarioRepository,
                             EntradaClinicaRepository entradaClinicaRepository) {
        this.pacienteRepository = pacienteRepository;
        this.profissionalRepository = profissionalRepository;
        this.prontuarioRepository = prontuarioRepository;
        this.entradaClinicaRepository = entradaClinicaRepository;
    }

    @Transactional
    public EntradaClinica registrarEntrada(UUID pacienteId, EntradaClinicaRequest request) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));
        ProfissionalDaSaude profissional = profissionalRepository.findById(request.getProfissionalId())
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado"));

        Prontuario prontuario = paciente.getProntuario();
        if (prontuario == null) {
            prontuario = new Prontuario();
            prontuario.setHistoricoSocial("Gerado automaticamente");
            paciente.vincularProntuario(prontuario);
        }

        EntradaClinica entrada = EntradaClinica.builder()
                .dataRegistro(LocalDateTime.now())
                .queixaPrincipal(request.getQueixaPrincipal())
                .avaliacao(request.getAvaliacao())
                .planoTerapeutico(request.getPlanoTerapeutico())
                .profissional(profissional)
                .build();

        prontuario.registrarEntrada(entrada);
        prontuarioRepository.save(prontuario);
        return entrada;
    }

    @Transactional(readOnly = true)
    public List<EntradaClinica> historicoCompleto(UUID pacienteId) {
        Prontuario prontuario = prontuarioRepository.findWithEntradasByPacienteId(pacienteId)
                .orElseThrow(() -> new IllegalArgumentException("Prontuário não encontrado para o paciente"));
        return prontuario.getEntradas();
    }
}
