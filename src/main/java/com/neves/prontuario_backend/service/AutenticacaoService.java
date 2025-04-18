package com.neves.prontuario_backend.service;

import com.neves.prontuario_backend.entity.AutenticacaoEntity;
import com.neves.prontuario_backend.repository.AutenticacaoRepository;
import com.neves.prontuario_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService {

    @Autowired
    private AutenticacaoRepository autenticacaoRepository;

    public String login(String username, String rawPassword) {
        Optional<AutenticacaoEntity> opt = autenticacaoRepository.findByUsername(username);

        if (opt.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        AutenticacaoEntity auth = opt.get();

        if (!BCrypt.checkpw(rawPassword, auth.getPassword())) {
            throw new RuntimeException("Senha incorreta.");
        }

        String token = JwtUtil.generateToken(username);
        auth.setToken(token);
        autenticacaoRepository.save(auth);

        return token;
    }

    public void logout(String token) {
        Optional<AutenticacaoEntity> opt = autenticacaoRepository.findByToken(token);
        opt.ifPresent(auth -> {
            auth.setToken(null);
            autenticacaoRepository.save(auth);
        });
    }

    public boolean validateToken(String token) {
        return JwtUtil.validateToken(token);
    }

    public Optional<AutenticacaoEntity> getByToken(String token) {
        return autenticacaoRepository.findByToken(token);
    }
}
