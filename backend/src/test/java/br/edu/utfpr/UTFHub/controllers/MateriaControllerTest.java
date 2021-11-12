package br.edu.utfpr.UTFHub.controllers;

import br.edu.utfpr.UTFHub.entities.Materia;
import br.edu.utfpr.UTFHub.entities.Usuario;
import br.edu.utfpr.UTFHub.service.MateriaService;
import br.edu.utfpr.UTFHub.stubs.MateriaStub;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class MateriaControllerTest {

    @Autowired
    MockMvc mockmvc;

    @Spy
    private ObjectMapper objectMapper;

    @Mock
    private MateriaService materiaService;

    @BeforeEach
    void setup(){
        this.mockmvc = MockMvcBuilders.standaloneSetup(new MateriaController(materiaService)).build();
    }


    @Test
    @SneakyThrows
    void insert() {
        when(materiaService.insert(any(Materia.class))).thenReturn(MateriaStub.createMateria());

        mockmvc.perform(post("/materia")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(MateriaStub.createMateria())))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void updateTrue() {
        when(materiaService.update(any())).thenReturn(Boolean.TRUE);

        mockmvc.perform(put("/materia")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(MateriaStub.createMateria())))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void updateFalse() {
        when(materiaService.update(any(Materia.class))).thenReturn(Boolean.FALSE);

        mockmvc.perform(put("/materia")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(MateriaStub.createMateria())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void deleteTrue() {
        when(materiaService.delete(any(Materia.class))).thenReturn(Boolean.TRUE);

        mockmvc.perform(delete("/materia")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(MateriaStub.createMateria())))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void deleteFalse() {
        when(materiaService.delete(any(Materia.class))).thenReturn(Boolean.FALSE);

        mockmvc.perform(delete("/materia")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(MateriaStub.createMateria())))
                .andExpect(status().isBadRequest());
    }

}