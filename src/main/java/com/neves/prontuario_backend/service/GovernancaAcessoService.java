package com.neves.prontuario_backend.service;

import com.neves.prontuario_backend.api.dto.EmpregadoProvisionamentoRequest;
import com.neves.prontuario_backend.domain.identity.*;
import com.neves.prontuario_backend.repository.EmpregadoRepository;
import com.neves.prontuario_backend.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class GovernancaAcessoService {

    private final EmpregadoRepository empregadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public GovernancaAcessoService(EmpregadoRepository empregadoRepository,
                                   UsuarioRepository usuarioRepository,
                                   PasswordEncoder passwordEncoder) {
        this.empregadoRepository = empregadoRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Empregado provisionarUsuario(EmpregadoProvisionamentoRequest request) {
        Empregado empregado;
        if (request.getPerfilPrincipal() == Perfil.PROFISSIONAL_SAUDE) {
            ProfissionalDaSaude profissional = new ProfissionalDaSaude();
            profissional.setFormacao(request.getFormacao());
            profissional.setEspecialidade(request.getEspecialidade());
            profissional.setRegistroConselho(request.getRegistroConselho());
            empregado = profissional;
        } else if (request.getPerfilPrincipal() == Perfil.ATENDIMENTO) {
            empregado = new Atendente();
        } else {
            empregado = new Administrador();
        }

        empregado.setNome(request.getNome());
        empregado.setEmail(request.getEmail());
        empregado.setMatricula(request.getMatricula());
        empregado.setAtivo(true);
        empregado.setPerfis(Set.of(request.getPerfilPrincipal()));

        Credencial credencial = Credencial.builder()
                .username(request.getEmail())
                .senhaHash(passwordEncoder.encode(request.getSenha()))
                .build();
        credencial.setUsuario(empregado);
        empregado.setCredencial(credencial);

        return empregadoRepository.save(empregado);
    }

    @Transactional
    public void ajustarPerfil(UUID usuarioId, Set<Perfil> novosPerfis) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        usuario.setPerfis(novosPerfis);
    }

    @Transactional
    public void desabilitarUsuario(UUID empregadoId) {
        Empregado empregado = empregadoRepository.findById(empregadoId)
                .orElseThrow(() -> new IllegalArgumentException("Empregado não encontrado"));
        empregado.setAtivo(false);
    }
}
