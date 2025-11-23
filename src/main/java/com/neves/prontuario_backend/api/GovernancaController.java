package com.neves.prontuario_backend.api;

import com.neves.prontuario_backend.api.dto.EmpregadoProvisionamentoRequest;
import com.neves.prontuario_backend.api.dto.PerfisRequest;
import com.neves.prontuario_backend.domain.identity.Empregado;
import com.neves.prontuario_backend.service.GovernancaAcessoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class GovernancaController {

    private final GovernancaAcessoService governancaAcessoService;

    public GovernancaController(GovernancaAcessoService governancaAcessoService) {
        this.governancaAcessoService = governancaAcessoService;
    }

    @PostMapping("/provisionar")
    public ResponseEntity<Empregado> provisionar(@Valid @RequestBody EmpregadoProvisionamentoRequest request) {
        return ResponseEntity.ok(governancaAcessoService.provisionarUsuario(request));
    }

    @PutMapping("/{usuarioId}/perfis")
    public ResponseEntity<Void> ajustarPerfis(@PathVariable UUID usuarioId,
                                              @Valid @RequestBody PerfisRequest request) {
        governancaAcessoService.ajustarPerfil(usuarioId, request.getPerfis());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{usuarioId}/desabilitar")
    public ResponseEntity<Void> desabilitar(@PathVariable UUID usuarioId) {
        governancaAcessoService.desabilitarUsuario(usuarioId);
        return ResponseEntity.noContent().build();
    }
}
