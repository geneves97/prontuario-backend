package com.neves.prontuario_backend.api.dto;

import java.time.LocalDateTime;

public class TokenResponse {

    private String token;
    private LocalDateTime expiraEm;

    public TokenResponse(String token, LocalDateTime expiraEm) {
        this.token = token;
        this.expiraEm = expiraEm;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getExpiraEm() {
        return expiraEm;
    }
}
