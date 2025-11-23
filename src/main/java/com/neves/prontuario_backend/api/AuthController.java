package com.neves.prontuario_backend.api;

import com.neves.prontuario_backend.api.dto.LoginRequest;
import com.neves.prontuario_backend.api.dto.TokenResponse;
import com.neves.prontuario_backend.domain.identity.Token;
import com.neves.prontuario_backend.service.AutenticacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AutenticacaoService autenticacaoService;

    public AuthController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        Token token = autenticacaoService.autenticar(request.getUsername(), request.getSenha());
        return ResponseEntity.ok(new TokenResponse(token.getValor(), token.getExpiraEm()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestParam("token") String tokenValor) {
        Token token = autenticacaoService.renovar(tokenValor);
        return ResponseEntity.ok(new TokenResponse(token.getValor(), token.getExpiraEm()));
    }
}
