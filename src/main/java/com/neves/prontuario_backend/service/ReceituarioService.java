package com.neves.prontuario_backend.service;

import com.neves.prontuario_backend.api.dto.MedicamentoRequest;
import com.neves.prontuario_backend.api.dto.ReceituarioRequest;
import com.neves.prontuario_backend.domain.clinico.EntradaClinica;
import com.neves.prontuario_backend.domain.clinico.Medicamento;
import com.neves.prontuario_backend.domain.clinico.Receituario;
import com.neves.prontuario_backend.domain.clinico.StatusReceituario;
import com.neves.prontuario_backend.domain.identity.ProfissionalDaSaude;
import com.neves.prontuario_backend.repository.EntradaClinicaRepository;
import com.neves.prontuario_backend.repository.ProfissionalRepository;
import com.neves.prontuario_backend.repository.ReceituarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ReceituarioService {

    private final EntradaClinicaRepository entradaClinicaRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ReceituarioRepository receituarioRepository;

    public ReceituarioService(EntradaClinicaRepository entradaClinicaRepository,
                              ProfissionalRepository profissionalRepository,
                              ReceituarioRepository receituarioRepository) {
        this.entradaClinicaRepository = entradaClinicaRepository;
        this.profissionalRepository = profissionalRepository;
        this.receituarioRepository = receituarioRepository;
    }

    @Transactional
    public Receituario emitir(UUID entradaId, UUID profissionalId, ReceituarioRequest request) {
        EntradaClinica entrada = entradaClinicaRepository.findById(entradaId)
                .orElseThrow(() -> new IllegalArgumentException("Entrada clínica não encontrada"));
        ProfissionalDaSaude profissional = profissionalRepository.findById(profissionalId)
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado"));

        Receituario receituario = Receituario.builder()
                .dataEmissao(LocalDate.now())
                .status(StatusReceituario.EMITIDO)
                .profissional(profissional)
                .build();

        for (MedicamentoRequest med : request.getMedicamentos()) {
            receituario.adicionarMedicamento(Medicamento.builder()
                    .nome(med.getNome())
                    .dosagem(med.getDosagem())
                    .frequencia(med.getFrequencia())
                    .quantidade(med.getQuantidade())
                    .build());
        }

        entrada.registrarReceituario(receituario);
        entradaClinicaRepository.save(entrada);
        return receituario;
    }

    @Transactional
    public void revogar(UUID receituarioId) {
        Receituario receituario = receituarioRepository.findById(receituarioId)
                .orElseThrow(() -> new IllegalArgumentException("Receituário não encontrado"));
        receituario.revogar();
    }
}
