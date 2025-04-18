package com.neves.prontuario_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ProfissionalDaSaude")
public class ProfissionalDaSaudeEntity extends EmpregadoEntity {

    @Column(name = "Formacao")
    private String formacao;

    @Column(name = "Especialidade")
    private String especialidade;

    @Column(name = "RegistroConselho")
    private String registroConselho;

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL)
    private List<ReceituarioEntity> receituariosEmitidos;

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL)
    private List<ProntuarioEntity> prontuariosEditados;

    @OneToOne
    @JoinColumn(name = "AutenticacaoID", referencedColumnName = "ID")
    private AutenticacaoEntity autenticacao;

    // --- Métodos do UML ---

    public void editarProntuario(PacienteEntity paciente) {
        // Lógica para editar prontuário do paciente
    }

    public void emitirReceituario(PacienteEntity paciente) {
        // Lógica para emitir receituário
    }
}
