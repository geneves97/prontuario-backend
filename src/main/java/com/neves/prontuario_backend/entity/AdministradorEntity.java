package com.neves.prontuario_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Administrador")
public class AdministradorEntity extends EmpregadoEntity {


    public void registrarNovoEmpregado(EmpregadoEntity empregado) {
        // Lógica para registrar novo empregado
        // Ex: salvar no repositório de empregados
    }

    public void registrarNovoPaciente(PacienteEntity paciente) {
        // Lógica para registrar novo paciente
    }

    public void editarDadosEmpregado(EmpregadoEntity empregado) {
        // Lógica para editar informações do empregado
    }

    public void apagarEmpregado(EmpregadoEntity empregado) {
        // Lógica para deletar um empregado
    }

    public void apagarPaciente(PacienteEntity paciente) {
        // Lógica para deletar um paciente
    }
}
