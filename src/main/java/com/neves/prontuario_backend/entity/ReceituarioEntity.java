package com.neves.prontuario_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Receituario")
public class ReceituarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DataEmissao")
    private Date dataEmissao;

    @Column(name = "MedicoID")
    private Integer medicoId;

    @Column(name = "MedicoNome")
    private String medicoNome;

    @ManyToOne
    @JoinColumn(name = "PacienteID")
    private PacienteEntity paciente;

    @ManyToOne
    @JoinColumn(name = "ProfissionalID")
    private ProfissionalDaSaudeEntity profissional;

    // Lista de medicamentos sem relacionamento direto com JPA
    @Transient
    private List<MedicamentoEntity> medicamentos;

    // --- Método para adicionar medicamento manualmente ---
    public void adicionarMedicamento(MedicamentoEntity medicamento) {
        if (medicamentos == null) {
            medicamentos = new java.util.ArrayList<>();
        }
        medicamentos.add(medicamento);
    }
}
