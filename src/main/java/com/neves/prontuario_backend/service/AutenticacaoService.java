package com.neves.prontuario_backend.service;

import com.neves.prontuario_backend.domain.identity.Credencial;
import com.neves.prontuario_backend.domain.identity.Token;
import com.neves.prontuario_backend.repository.CredencialRepository;
import com.neves.prontuario_backend.repository.TokenRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AutenticacaoService {

    private final CredencialRepository credencialRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public AutenticacaoService(CredencialRepository credencialRepository,
                               TokenRepository tokenRepository,
                               PasswordEncoder passwordEncoder) {
        this.credencialRepository = credencialRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Token autenticar(String username, String senha) {
        Credencial credencial = credencialRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        if (!passwordEncoder.matches(senha, credencial.getSenhaHash())) {
            throw new IllegalArgumentException("Senha inválida");
        }
        Token token = Token.builder()
                .valor(UUID.randomUUID().toString())
                .expiraEm(LocalDateTime.now().plusHours(2))
                .build();
        credencial.registrarLogin(token);
        credencialRepository.save(credencial);
        return token;
    }

    @Transactional
    public Token renovar(String tokenValor) {
        Token token = tokenRepository.findByValor(tokenValor)
                .orElseThrow(() -> new IllegalArgumentException("Token não encontrado"));
        if (token.estaExpirado()) {
            throw new IllegalStateException("Token expirado, faça login novamente");
        }
        Token novoToken = Token.builder()
                .valor(UUID.randomUUID().toString())
                .expiraEm(LocalDateTime.now().plusHours(2))
                .credencial(token.getCredencial())
                .build();
        token.getCredencial().registrarLogin(novoToken);
        credencialRepository.save(token.getCredencial());
        return novoToken;
    }
}
