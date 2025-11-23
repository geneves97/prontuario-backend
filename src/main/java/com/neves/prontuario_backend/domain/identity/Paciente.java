package com.neves.prontuario_backend.domain.identity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neves.prontuario_backend.domain.clinico.Prontuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("PACIENTE")
public class Paciente extends Usuario {

    @Column(nullable = true)
    private String contato;

    @Column(nullable = true)
    private LocalDate dataNascimento;

    @JsonIgnore
    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Prontuario prontuario;

    public void vincularProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
        prontuario.setPaciente(this);
    }
}
