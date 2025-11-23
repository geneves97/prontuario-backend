package com.neves.prontuario_backend.api.dto;

import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ReceituarioRequest {

    @NotNull
    private List<MedicamentoRequest> medicamentos = new ArrayList<>();

    public List<MedicamentoRequest> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<MedicamentoRequest> medicamentos) {
        this.medicamentos = medicamentos;
    }
}
