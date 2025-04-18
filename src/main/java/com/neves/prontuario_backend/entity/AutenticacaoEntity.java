package com.neves.prontuario_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Autenticacao")
public class AutenticacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Username", unique = true, nullable = false)
    private String username;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Token")
    private String token;

    // Métodos utilitários (lógica real virá no service)

    public String login(String username, String password) {
        // Esta lógica será feita no service
        return null;
    }

    public void logout() {
        this.token = null;
    }

    public boolean validateToken(String token) {
        return this.token != null && this.token.equals(token);
    }
}
