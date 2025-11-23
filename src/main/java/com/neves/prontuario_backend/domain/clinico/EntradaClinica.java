package com.neves.prontuario_backend.domain.clinico;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neves.prontuario_backend.domain.identity.ProfissionalDaSaude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class EntradaClinica {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime dataRegistro;

    @Column(nullable = false)
    private String queixaPrincipal;

    @Column(nullable = false, length = 2000)
    private String avaliacao;

    @Column(nullable = false, length = 2000)
    private String planoTerapeutico;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prontuario_id")
    private Prontuario prontuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissional_id")
    private ProfissionalDaSaude profissional;

    @OneToOne(mappedBy = "entrada", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Receituario receituario;

    public void registrarReceituario(Receituario receituario) {
        this.receituario = receituario;
        receituario.setEntrada(this);
    }
}
