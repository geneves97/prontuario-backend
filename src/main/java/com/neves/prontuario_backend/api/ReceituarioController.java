package com.neves.prontuario_backend.api;

import com.neves.prontuario_backend.api.dto.ReceituarioRequest;
import com.neves.prontuario_backend.domain.clinico.Receituario;
import com.neves.prontuario_backend.service.ReceituarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ReceituarioController {

    private final ReceituarioService receituarioService;

    public ReceituarioController(ReceituarioService receituarioService) {
        this.receituarioService = receituarioService;
    }

    @PostMapping("/entradas/{entradaId}/receituarios")
    public ResponseEntity<Receituario> emitir(@PathVariable UUID entradaId,
                                              @RequestParam UUID profissionalId,
                                              @Valid @RequestBody ReceituarioRequest request) {
        return ResponseEntity.ok(receituarioService.emitir(entradaId, profissionalId, request));
    }

    @PostMapping("/receituarios/{id}/revogar")
    public ResponseEntity<Void> revogar(@PathVariable UUID id) {
        receituarioService.revogar(id);
        return ResponseEntity.noContent().build();
    }
}
