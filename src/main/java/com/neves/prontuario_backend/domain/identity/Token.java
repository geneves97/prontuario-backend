package com.neves.prontuario_backend.domain.identity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Token {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String valor;

    @Column(nullable = false)
    private LocalDateTime expiraEm;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credencial_id")
    private Credencial credencial;

    public boolean estaExpirado() {
        return LocalDateTime.now().isAfter(expiraEm);
    }
}
