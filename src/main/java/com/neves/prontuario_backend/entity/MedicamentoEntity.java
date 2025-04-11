package com.neves.prontuario_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MedicamentoEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @SequenceGenerator(name="id")
    @Column(name = "ID")
    Integer id;

    @Column(name = "nome")
    String nome;

    @Column(name = "dose")
    Float dose;


}
