package br.edu.utfpr.UTFHub.stubs;

import br.edu.utfpr.UTFHub.dto.UsuarioDTO;
import br.edu.utfpr.UTFHub.entities.Materia;
import br.edu.utfpr.UTFHub.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

public class UsuarioStub {
    public static Page<Usuario> createPage() {
        return new PageImpl<>(Collections.singletonList(createUsuario()));
    }
    public static Usuario createUsuario() {
        return Usuario.builder()
                .id(10L)
                .nome("nome")
                .email("email")
                .senha("senha")
                .campus("campus")
                .curso("curso")
                .build();
    }

    public static UsuarioDTO createUsuarioDTO() {
        return UsuarioDTO.builder()
                .id(10L)
                .nome("nome")
                .email("email")
                .campus("campus")
                .curso("curso")
                .build();
    }

    public static Usuario createUsuarioDB() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("senha");
        return Usuario.builder()
                .id(10L)
                .nome("nome")
                .email("email")
                .senha(encodedPassword)
                .campus("campus")
                .curso("curso")
                .build();
    }
}
