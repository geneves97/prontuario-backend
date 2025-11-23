package com.neves.prontuario_backend.domain.identity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("PROFISSIONAL_SAUDE")
public class ProfissionalDaSaude extends Empregado {

    @Column(nullable = true)
    private String formacao;

    @Column(nullable = true)
    private String especialidade;

    @Column(nullable = true, unique = true)
    private String registroConselho;
}
