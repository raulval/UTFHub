package br.edu.utfpr.UTFHub.stubs;


import br.edu.utfpr.UTFHub.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;

public class PostStub {
    public static Page<Post> createPage() {
        return new PageImpl<>(Collections.singletonList(createPost()));
    }
    public static Post createPost() {
        return Post.builder()
                .id(10L)
                .autor("nome")
                .pergunta("pergunta")
                .materiaId(10L)
                .autorId(10L)
                .build();
    }
}
