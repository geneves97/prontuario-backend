package com.neves.prontuario_backend.config;

import com.neves.prontuario_backend.domain.clinico.Prontuario;
import com.neves.prontuario_backend.domain.identity.*;
import com.neves.prontuario_backend.repository.PacienteRepository;
import com.neves.prontuario_backend.repository.ProfissionalRepository;
import com.neves.prontuario_backend.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Set;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedUsuarios(UsuarioRepository usuarioRepository,
                                  PacienteRepository pacienteRepository,
                                  ProfissionalRepository profissionalRepository,
                                  PasswordEncoder encoder) {
        return args -> {
            if (usuarioRepository.count() > 0) {
                return;
            }

            Administrador admin = new Administrador();
            admin.setNome("Admin Geral");
            admin.setEmail("admin@sistema.test");
            admin.setMatricula("ADM-001");
            admin.setAtivo(true);
            admin.setPerfis(Set.of(Perfil.ADMINISTRADOR));
            Credencial credAdmin = Credencial.builder()
                    .username(admin.getEmail())
                    .senhaHash(encoder.encode("admin123"))
                    .build();
            credAdmin.setUsuario(admin);
            admin.setCredencial(credAdmin);
            usuarioRepository.save(admin);

            ProfissionalDaSaude profissional = new ProfissionalDaSaude();
            profissional.setNome("Dra. Clara Saúde");
            profissional.setEmail("clara@sistema.test");
            profissional.setMatricula("PRO-100");
            profissional.setAtivo(true);
            profissional.setPerfis(Set.of(Perfil.PROFISSIONAL_SAUDE));
            profissional.setFormacao("Medicina");
            profissional.setEspecialidade("Clínica Geral");
            profissional.setRegistroConselho("CRM-12345");
            Credencial credProf = Credencial.builder()
                    .username(profissional.getEmail())
                    .senhaHash(encoder.encode("prof123"))
                    .build();
            credProf.setUsuario(profissional);
            profissional.setCredencial(credProf);
            profissionalRepository.save(profissional);

            Paciente paciente = new Paciente();
            paciente.setNome("Paciente Demo");
            paciente.setEmail("paciente@sistema.test");
            paciente.setContato("11999999999");
            paciente.setDataNascimento(LocalDate.of(1990, 1, 1));
            paciente.setPerfis(Set.of(Perfil.PACIENTE));
            Credencial credPaciente = Credencial.builder()
                    .username(paciente.getEmail())
                    .senhaHash(encoder.encode("paciente123"))
                    .build();
            credPaciente.setUsuario(paciente);
            paciente.setCredencial(credPaciente);

            Prontuario prontuario = Prontuario.builder()
                    .historicoSocial("Paciente de demonstração")
                    .build();
            paciente.vincularProntuario(prontuario);
            pacienteRepository.save(paciente);
        };
    }
}
