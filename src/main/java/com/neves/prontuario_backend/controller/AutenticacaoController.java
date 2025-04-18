package com.neves.prontuario_backend.controller;

import com.neves.prontuario_backend.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return autenticacaoService.login(username, password);
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String token) {
        autenticacaoService.logout(token.replace("Bearer ", ""));
    }

    @GetMapping("/validate")
    public boolean validate(@RequestHeader("Authorization") String token) {
        return autenticacaoService.validateToken(token.replace("Bearer ", ""));
    }
}
