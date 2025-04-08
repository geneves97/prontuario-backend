package com.neves.prontuario_backend.entity;

import jakarta.persistence.*;

public class EmpregadoEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @SequenceGenerator(name="id")
    @Column(name = "ID")
    Integer id;

    @Column(name="nome")
    String nome;

    @Column(name="email")
    String email;

    @Column(name="cargo")
    String cargo;

    public void visualizarProntuario (){
        return ;
    }

    public void visualizarReceituario (){
        return ;
    }

    public void criarProntuario (){
        return ;
    }

    public void editarPaciente (){
        return ;
    }
}
