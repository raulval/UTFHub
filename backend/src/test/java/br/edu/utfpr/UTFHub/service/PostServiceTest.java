package br.edu.utfpr.UTFHub.service;


import br.edu.utfpr.UTFHub.entities.Post;
import br.edu.utfpr.UTFHub.repositories.PostRepository;
import br.edu.utfpr.UTFHub.stubs.PostStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Test
    @DisplayName("Find all should return all posts")
    void findAll() {
        when(postRepository.findAll(any(Pageable.class))).thenReturn(PostStub.createPage());
        postService.findAll(PageRequest.of(0,10));
        verify(postRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @DisplayName("Find post by materia id should return posts with materia id")
    void findByMateriaId() {
        when(postRepository.findAllByMateriaId(eq(10L) ,any(Pageable.class))).thenReturn(PostStub.createPage());
        postService.findByMateriaId(10L, PageRequest.of(0,10));
        verify(postRepository, times(1)).findAllByMateriaId(eq(10L), any(Pageable.class));
    }

    @Test
    @DisplayName("insert should insert post")
    void insert() {
        when(postRepository.save(any(Post.class))).thenReturn(PostStub.createPost());
        Post post = postService.insert(PostStub.createPost());
        assertEquals(10L, post.getId());
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    void updateTrue() {
        when(postRepository.findById(10L)).thenReturn(Optional.of(PostStub.createPost()));
        when(postRepository.save(any(Post.class))).thenReturn(PostStub.createPost());
        Boolean post = postService.update(PostStub.createPost(), 10L);
        assertEquals(true, post);
        verify(postRepository, times(1)).save(any(Post.class));
        verify(postRepository, times(1)).findById(10L);
    }

    @Test
    void updateFalse() {
        when(postRepository.findById(10L)).thenReturn(Optional.empty());
        Boolean post = postService.update(PostStub.createPost(), 10L);
        assertEquals(false, post);
        verify(postRepository, never()).save(any(Post.class));
        verify(postRepository, times(1)).findById(10L);
    }


    @Test
    void deleteTrue() {
        when(postRepository.findById(10L)).thenReturn(Optional.of(PostStub.createPost()));
        doNothing().when(postRepository).deleteById(10L);
        Boolean post = postService.deletePost(10L);
        assertEquals(true, post);
        verify(postRepository, times(1)).deleteById(10L);
        verify(postRepository, times(1)).findById(10L);
    }

    @Test
    void deleteFalse() {
        when(postRepository.findById(10L)).thenReturn(Optional.empty());
        Boolean post = postService.deletePost(10L);
        assertEquals(false, post);
        verify(postRepository, never()).deleteById(10L);
        verify(postRepository, times(1)).findById(10L);
    }
}