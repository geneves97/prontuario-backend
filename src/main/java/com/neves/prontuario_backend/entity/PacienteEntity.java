package com.neves.prontuario_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Paciente")
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Nome")
    private String nome;

    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
    private ProntuarioEntity prontuario;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<ReceituarioEntity> receituarios;

    @OneToOne
    @JoinColumn(name = "AutenticacaoID", referencedColumnName = "ID")
    private AutenticacaoEntity autenticacao;

    public void visualizarProntuario() {
        // Lógica para visualização do prontuário
    }

    public void visualizarReceituarios() {
        // Lógica para visualização de receituários
    }
}
