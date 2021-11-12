package br.edu.utfpr.UTFHub.service;

import br.edu.utfpr.UTFHub.dto.UsuarioDTO;
import br.edu.utfpr.UTFHub.entities.Materia;
import br.edu.utfpr.UTFHub.entities.Usuario;
import br.edu.utfpr.UTFHub.repositories.MateriaRepository;
import br.edu.utfpr.UTFHub.repositories.UsuarioRepository;
import br.edu.utfpr.UTFHub.stubs.MateriaStub;
import br.edu.utfpr.UTFHub.stubs.UsuarioStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void findAll() {
        when(usuarioRepository.findAll(any(Pageable.class))).thenReturn(UsuarioStub.createPage());
        usuarioService.findAll(PageRequest.of(0,10));
        verify(usuarioRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void insertTrue() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(UsuarioStub.createUsuario());
        when(usuarioRepository.countEmail("email")).thenReturn(0);
        UsuarioDTO usuario = usuarioService.insert(UsuarioStub.createUsuario());
        assertEquals(10L, usuario.getId());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
        verify(usuarioRepository, times(1)).countEmail("email");
    }

    @Test
    void insertFalse() {
        when(usuarioRepository.countEmail("email")).thenReturn(5);
        UsuarioDTO usuario = usuarioService.insert(UsuarioStub.createUsuario());
        assertNull(usuario);
        verify(usuarioRepository, never()).save(any(Usuario.class));
        verify(usuarioRepository, times(1)).countEmail("email");
    }

    @Test
    void updateTrue() {
        when(usuarioRepository.findById(10L)).thenReturn(Optional.of(UsuarioStub.createUsuarioDB()));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(UsuarioStub.createUsuario());
        Boolean usuario = usuarioService.update(UsuarioStub.createUsuario());
        assertEquals(true, usuario);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
        verify(usuarioRepository, times(1)).findById(10L);
    }

    @Test
    void updateFalse() {
        when(usuarioRepository.findById(10L)).thenReturn(Optional.empty());
        Boolean usuario = usuarioService.update(UsuarioStub.createUsuario());
        assertEquals(false, usuario);
        verify(usuarioRepository, never()).save(any(Usuario.class));
        verify(usuarioRepository, times(1)).findById(10L);
    }

    @Test
    void deleteTrue() {
        when(usuarioRepository.findById(10L)).thenReturn(Optional.of(UsuarioStub.createUsuarioDB()));
        doNothing().when(usuarioRepository).delete(any(Usuario.class));
        Boolean usuario = usuarioService.delete(UsuarioStub.createUsuario());
        assertEquals(true, usuario);
        verify(usuarioRepository, times(1)).delete(any(Usuario.class));
        verify(usuarioRepository, times(1)).findById(10L);
    }

    @Test
    void deleteFalse() {
        when(usuarioRepository.findById(10L)).thenReturn(Optional.empty());
        Boolean usuario = usuarioService.delete(UsuarioStub.createUsuario());
        assertEquals(false, usuario);
        verify(usuarioRepository, never()).delete(any(Usuario.class));
        verify(usuarioRepository, times(1)).findById(10L);
    }

    @Test
    void loginTrue() {
        when(usuarioRepository.findByEmail("email")).thenReturn(UsuarioStub.createUsuarioDB());
        UsuarioDTO usuario = usuarioService.login("email", "senha");
        verify(usuarioRepository, times(1)).findByEmail("email");
        assertEquals(10L, usuario.getId());
    }

    @Test
    void loginFalse() {
        when(usuarioRepository.findByEmail("email")).thenReturn(null);
        UsuarioDTO usuario = usuarioService.login("email", "senha");
        verify(usuarioRepository, times(1)).findByEmail("email");
        assertNull(usuario);
    }
}