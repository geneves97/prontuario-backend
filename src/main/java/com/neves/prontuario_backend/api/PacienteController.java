package com.neves.prontuario_backend.api;

import com.neves.prontuario_backend.api.dto.PacienteAtualizacaoRequest;
import com.neves.prontuario_backend.api.dto.PacienteCadastroRequest;
import com.neves.prontuario_backend.domain.identity.Paciente;
import com.neves.prontuario_backend.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Paciente> registrar(@Valid @RequestBody PacienteCadastroRequest request) {
        return ResponseEntity.ok(pacienteService.registrarPaciente(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizar(@PathVariable UUID id,
                                              @Valid @RequestBody PacienteAtualizacaoRequest request) {
        return ResponseEntity.ok(pacienteService.atualizarPaciente(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable UUID id) {
        return ResponseEntity.ok(pacienteService.buscarPaciente(id));
    }
}
