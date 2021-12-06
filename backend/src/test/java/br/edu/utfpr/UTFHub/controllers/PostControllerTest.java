package br.edu.utfpr.UTFHub.controllers;


import br.edu.utfpr.UTFHub.entities.Post;
import br.edu.utfpr.UTFHub.service.PostService;
import br.edu.utfpr.UTFHub.stubs.PostStub;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    MockMvc mockmvc;

    @Spy
    private ObjectMapper objectMapper;

    @Mock
    private PostService postService;

    @BeforeEach
    void setup(){
        this.mockmvc = MockMvcBuilders.standaloneSetup(new PostController(postService)).build();
    }


    @Test
    @SneakyThrows
    void insert() {
        when(postService.insert(any(Post.class))).thenReturn(PostStub.createPost());

        mockmvc.perform(MockMvcRequestBuilders.post("/materia/{materiaId}/posts", 10L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(PostStub.createPost())))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void updateTrue() {
        when(postService.update(any(), eq(10L))).thenReturn(Boolean.TRUE);

        mockmvc.perform(MockMvcRequestBuilders.put("/posts/{id}/edit", 10L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(PostStub.createPost())))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void updateFalse() {
        when(postService.update(any(Post.class), eq(10L))).thenReturn(Boolean.FALSE);

        mockmvc.perform(MockMvcRequestBuilders.put("/posts/{id}/edit", 10L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(PostStub.createPost())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void deleteTrue() {
        when(postService.deletePost(10L)).thenReturn(Boolean.TRUE);

        mockmvc.perform(MockMvcRequestBuilders.delete("/posts/{id}/delete", 10L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(PostStub.createPost())))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void deleteFalse() {
        when(postService.deletePost(10L)).thenReturn(Boolean.FALSE);

        mockmvc.perform(MockMvcRequestBuilders.delete("/posts/{id}/delete", 10L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(PostStub.createPost())))
                .andExpect(status().isBadRequest());
    }

}