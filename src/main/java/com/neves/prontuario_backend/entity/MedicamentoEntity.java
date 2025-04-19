package com.neves.prontuario_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Medicamento")
public class MedicamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @SequenceGenerator(name = "id")
    private Integer id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Dose")
    private Double dose;
}
