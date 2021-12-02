package br.edu.utfpr.UTFHub.stubs;

import br.edu.utfpr.UTFHub.entities.Materia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;

public class MateriaStub {
    public static Page<Materia> createPage() {
        return new PageImpl<>(Collections.singletonList(createMateria()));
    }
    public static Materia createMateria() {
        return Materia.builder()
                .id(10L)
                .nome("nome")
                .tipo(0L)
                .build();
    }
}
