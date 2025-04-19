package com.neves.prontuario_backend.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("ADMINISTRADOR")
public class AdministradorEntity extends EmpregadoEntity {

    public void registrarNovoEmpregado(EmpregadoEntity empregado) {
        // lógica
    }

    public void registrarNovoPaciente(PacienteEntity paciente) {
        // lógica
    }

    public void editarDadosEmpregado(EmpregadoEntity empregado) {
        // lógica
    }

    public void apagarEmpregado(EmpregadoEntity empregado) {
        // lógica
    }

    public void apagarPaciente(PacienteEntity paciente) {
        // lógica
    }
}
