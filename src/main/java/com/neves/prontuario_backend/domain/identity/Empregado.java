package com.neves.prontuario_backend.domain.identity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("EMPREGADO")
public class Empregado extends Usuario {

    @Column(nullable = true, unique = true)
    private String matricula;

    @Column(nullable = true)
    private boolean ativo;
}
