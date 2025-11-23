package com.neves.prontuario_backend.domain.identity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Credencial {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String senhaHash;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "credencial", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Token> tokensAtivos = new ArrayList<>();

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public void registrarLogin(Token token) {
        tokensAtivos.add(token);
        token.setCredencial(this);
    }

    public void revogarToken(Token token) {
        tokensAtivos.remove(token);
    }
}
