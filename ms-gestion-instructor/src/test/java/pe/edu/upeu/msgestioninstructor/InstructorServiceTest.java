package pe.edu.upeu.msgestioninstructor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upeu.msgestioninstructor.entity.InstructorEntity;
import pe.edu.upeu.msgestioninstructor.errors.InstructorNotFoundException;
import pe.edu.upeu.msgestioninstructor.mappers.InstructorMapper;
import pe.edu.upeu.msgestioninstructor.repository.InstructorRepository;
import pe.edu.upeu.msgestioninstructor.service.InstructorService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstructorServiceTest {

    @Mock
    private InstructorRepository repository;

    @Mock
    private InstructorMapper mapper;

    @InjectMocks
    private InstructorService service;

    @Test
    void buscarPorId_noExiste_lanzaExcepcion() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(InstructorNotFoundException.class, () -> service.buscarPorId(99L));
    }

    @Test
    void buscarPorId_inactivo_lanzaExcepcion() {
        InstructorEntity entity = new InstructorEntity();
        entity.setId(1L);
        entity.setEstado("INACTIVO");
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        assertThrows(IllegalArgumentException.class, () -> service.buscarPorId(1L));
    }

    @Test
    void crear_dniDuplicado_lanzaExcepcion() {
        InstructorEntity existente = new InstructorEntity();
        existente.setDni("87654321");
        var request = new pe.edu.upeu.msgestioninstructor.dto.InstructorRequest();
        request.setDni("87654321");
        when(repository.findByDni("87654321")).thenReturn(Optional.of(existente));
        assertThrows(IllegalArgumentException.class, () -> service.crear(request));
    }
}