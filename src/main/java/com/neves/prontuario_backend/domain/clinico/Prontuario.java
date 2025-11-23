package com.neves.prontuario_backend.domain.clinico;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neves.prontuario_backend.domain.identity.Paciente;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Prontuario {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String historicoSocial;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @OneToMany(mappedBy = "prontuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<EntradaClinica> entradas = new ArrayList<>();

    public void registrarEntrada(EntradaClinica entrada) {
        entrada.setProntuario(this);
        entradas.add(entrada);
    }

    public List<Receituario> obterReceituarios() {
        return entradas.stream()
                .map(EntradaClinica::getReceituario)
                .filter(r -> r != null)
                .toList();
    }
}
