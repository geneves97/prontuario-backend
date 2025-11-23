package com.neves.prontuario_backend.api;

import com.neves.prontuario_backend.api.dto.EntradaClinicaRequest;
import com.neves.prontuario_backend.domain.clinico.EntradaClinica;
import com.neves.prontuario_backend.service.ProntuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pacientes/{pacienteId}/prontuario")
public class ProntuarioController {

    private final ProntuarioService prontuarioService;

    public ProntuarioController(ProntuarioService prontuarioService) {
        this.prontuarioService = prontuarioService;
    }

    @PostMapping("/entradas")
    public ResponseEntity<EntradaClinica> registrarEntrada(@PathVariable UUID pacienteId,
                                                           @Valid @RequestBody EntradaClinicaRequest request) {
        return ResponseEntity.ok(prontuarioService.registrarEntrada(pacienteId, request));
    }

    @GetMapping("/entradas")
    public ResponseEntity<List<EntradaClinica>> historico(@PathVariable UUID pacienteId) {
        return ResponseEntity.ok(prontuarioService.historicoCompleto(pacienteId));
    }
}
