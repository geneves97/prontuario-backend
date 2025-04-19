package com.neves.prontuario_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Prontuario")
public class ProntuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @SequenceGenerator(name = "id")
    private Integer id;

    @Column(name = "CodigoModeloAnamnese")
    private Integer codigoModeloAnamnese;

    @Column(name = "QueixaPrincipal")
    private String queixaPrincipal;

    @Column(name = "HDA")
    private String hda;

    @Column(name = "AntecedentesPessoais")
    private String antecedentesPessoais;

    @Column(name = "InterrogatorioSistematico")
    private String interrogatorioSistematico;

    @Column(name = "HistoriaPsicosocial")
    private String historiaPsicosocial;

}
