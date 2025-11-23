package com.neves.prontuario_backend.domain.identity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@DiscriminatorValue("ADMINISTRADOR")
public class Administrador extends Empregado {
}
