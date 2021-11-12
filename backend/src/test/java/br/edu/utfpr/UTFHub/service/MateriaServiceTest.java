package br.edu.utfpr.UTFHub.service;

import br.edu.utfpr.UTFHub.entities.Materia;
import br.edu.utfpr.UTFHub.repositories.MateriaRepository;
import br.edu.utfpr.UTFHub.stubs.MateriaStub;
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
class MateriaServiceTest {

    @InjectMocks
    private MateriaService materiaService;

    @Mock
    private MateriaRepository materiaRepository;

    @Test
    @DisplayName("Find all should return all materias")
    void findAll() {
        when(materiaRepository.findAll(any(Pageable.class))).thenReturn(MateriaStub.createPage());
        materiaService.findAll(PageRequest.of(0,10));
        verify(materiaRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @DisplayName("insert should insert materias")
    void insert() {
        when(materiaRepository.save(any(Materia.class))).thenReturn(MateriaStub.createMateria());
        Materia materia = materiaService.insert(MateriaStub.createMateria());
        assertEquals(10L, materia.getId());
        verify(materiaRepository, times(1)).save(any(Materia.class));
    }

    @Test
    void updateTrue() {
        when(materiaRepository.findById(10L)).thenReturn(Optional.of(MateriaStub.createMateria()));
        when(materiaRepository.save(any(Materia.class))).thenReturn(MateriaStub.createMateria());
        Boolean materia = materiaService.update(MateriaStub.createMateria());
        assertEquals(true, materia);
        verify(materiaRepository, times(1)).save(any(Materia.class));
        verify(materiaRepository, times(1)).findById(10L);
    }

    @Test
    void updateFalse() {
        when(materiaRepository.findById(10L)).thenReturn(Optional.empty());
        Boolean materia = materiaService.update(MateriaStub.createMateria());
        assertEquals(false, materia);
        verify(materiaRepository, never()).save(any(Materia.class));
        verify(materiaRepository, times(1)).findById(10L);
    }


    @Test
    void deleteTrue() {
        when(materiaRepository.findById(10L)).thenReturn(Optional.of(MateriaStub.createMateria()));
        doNothing().when(materiaRepository).delete(any(Materia.class));
        Boolean materia = materiaService.delete(MateriaStub.createMateria());
        assertEquals(true, materia);
        verify(materiaRepository, times(1)).delete(any(Materia.class));
        verify(materiaRepository, times(1)).findById(10L);
    }

    @Test
    void deleteFalse() {
        when(materiaRepository.findById(10L)).thenReturn(Optional.empty());
        Boolean materia = materiaService.delete(MateriaStub.createMateria());
        assertEquals(false, materia);
        verify(materiaRepository, never()).delete(any(Materia.class));
        verify(materiaRepository, times(1)).findById(10L);
    }
}