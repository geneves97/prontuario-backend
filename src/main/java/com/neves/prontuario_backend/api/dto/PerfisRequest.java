package com.neves.prontuario_backend.api.dto;

import com.neves.prontuario_backend.domain.identity.Perfil;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public class PerfisRequest {

    @NotEmpty
    private Set<Perfil> perfis;

    public Set<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(Set<Perfil> perfis) {
        this.perfis = perfis;
    }
}
