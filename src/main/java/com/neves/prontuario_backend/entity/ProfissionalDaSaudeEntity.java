package com.neves.prontuario_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue("PROFISSIONAL_SAUDE")
public class ProfissionalDaSaudeEntity extends EmpregadoEntity {

    @Column(name = "Formacao")
    private String formacao;

    @Column(name = "Especialidade")
    private String especialidade;

    @Column(name = "RegistroConselho")
    private String registroConselho;

    // --- Métodos do UML ---

    public void editarProntuario(PacienteEntity paciente) {
        // Lógica para editar prontuário do paciente
    }

    public void emitirReceituario(PacienteEntity paciente) {
        // Lógica para emitir receituário
    }
}
