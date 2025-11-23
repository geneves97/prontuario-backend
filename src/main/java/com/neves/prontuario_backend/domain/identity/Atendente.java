package com.neves.prontuario_backend.domain.identity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@DiscriminatorValue("ATENDENTE")
public class Atendente extends Empregado {
}
