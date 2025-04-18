package com.neves.prontuario_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Empregado")
public class EmpregadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @SequenceGenerator(name = "id")
    private Integer id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Email")
    private String email;

    @Column(name = "Cargo")
    private String cargo;

    @OneToMany(mappedBy = "empregadoEditor", cascade = CascadeType.ALL)
    private List<PacienteEntity> pacientesEditados;

    @OneToMany(mappedBy = "empregadoVisualizador", cascade = CascadeType.ALL)
    private List<ReceituarioEntity> receituariosVisualizados;

    @OneToOne
    @JoinColumn(name = "AutenticacaoID", referencedColumnName = "ID")
    private AutenticacaoEntity autenticacao;
}
