package br.edu.utfpr.UTFHub.controllers;

import br.edu.utfpr.UTFHub.entities.Materia;
import br.edu.utfpr.UTFHub.entities.Usuario;
import br.edu.utfpr.UTFHub.service.MateriaService;
import br.edu.utfpr.UTFHub.service.UsuarioService;
import br.edu.utfpr.UTFHub.stubs.MateriaStub;
import br.edu.utfpr.UTFHub.stubs.UsuarioStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class UsuarioControllerTest {

    @Autowired
    MockMvc mockmvc;

    @Spy
    private ObjectMapper objectMapper;

    @Mock
    private UsuarioService usuarioService;

    @BeforeEach
    void setup(){
        this.mockmvc = MockMvcBuilders.standaloneSetup(new UsuarioController(usuarioService)).build();
    }


    @Test
    @SneakyThrows
    void insert() {
        when(usuarioService.insert(any(Usuario.class))).thenReturn(UsuarioStub.createUsuarioDTO());

        mockmvc.perform(post("/usuario")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(MateriaStub.createMateria())))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void updateTrue() {
        when(usuarioService.update(any())).thenReturn(Boolean.TRUE);

        mockmvc.perform(put("/usuario")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(MateriaStub.createMateria())))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void updateFalse() {
        when(usuarioService.update(any(Usuario.class))).thenReturn(Boolean.FALSE);

        mockmvc.perform(put("/usuario")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(MateriaStub.createMateria())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void deleteTrue() {
        when(usuarioService.delete(any(Usuario.class))).thenReturn(Boolean.TRUE);

        mockmvc.perform(delete("/usuario")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(MateriaStub.createMateria())))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void deleteFalse() {
        when(usuarioService.delete(any(Usuario.class))).thenReturn(Boolean.FALSE);

        mockmvc.perform(delete("/usuario")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(MateriaStub.createMateria())))
                .andExpect(status().isBadRequest());
    }

}