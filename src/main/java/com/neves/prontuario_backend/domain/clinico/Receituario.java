package com.neves.prontuario_backend.domain.clinico;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neves.prontuario_backend.domain.identity.ProfissionalDaSaude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Receituario {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private LocalDate dataEmissao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusReceituario status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissional_id")
    private ProfissionalDaSaude profissional;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "receituario_id")
    @Builder.Default
    private List<Medicamento> medicamentos = new ArrayList<>();

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrada_id")
    private EntradaClinica entrada;

    public void adicionarMedicamento(Medicamento medicamento) {
        medicamentos.add(medicamento);
    }

    public void revogar() {
        this.status = StatusReceituario.REVOGADO;
    }
}
