package com.neves.prontuario_backend.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class EntradaClinicaRequest {

    @NotBlank
    private String queixaPrincipal;

    @NotBlank
    private String avaliacao;

    @NotBlank
    private String planoTerapeutico;

    @NotNull
    private UUID profissionalId;

    public String getQueixaPrincipal() {
        return queixaPrincipal;
    }

    public void setQueixaPrincipal(String queixaPrincipal) {
        this.queixaPrincipal = queixaPrincipal;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getPlanoTerapeutico() {
        return planoTerapeutico;
    }

    public void setPlanoTerapeutico(String planoTerapeutico) {
        this.planoTerapeutico = planoTerapeutico;
    }

    public UUID getProfissionalId() {
        return profissionalId;
    }

    public void setProfissionalId(UUID profissionalId) {
        this.profissionalId = profissionalId;
    }
}
