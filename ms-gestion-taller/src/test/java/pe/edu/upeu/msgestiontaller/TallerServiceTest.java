package pe.edu.upeu.msgestiontaller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upeu.msgestiontaller.client.AlumnoClient;
import pe.edu.upeu.msgestiontaller.client.InstructorClient;
import pe.edu.upeu.msgestiontaller.entity.TallerEntity;
import pe.edu.upeu.msgestiontaller.errors.TallerNotFoundException;
import pe.edu.upeu.msgestiontaller.mappers.TallerMapper;
import pe.edu.upeu.msgestiontaller.repository.TallerRepository;
import pe.edu.upeu.msgestiontaller.service.TallerService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TallerServiceTest {

    @Mock
    private TallerRepository repository;

    @Mock
    private TallerMapper mapper;

    @Mock
    private InstructorClient instructorClient;

    @Mock
    private AlumnoClient alumnoClient;

    @InjectMocks
    private TallerService service;

    @Test
    void buscarPorId_noExiste_lanzaExcepcion() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(TallerNotFoundException.class, () -> service.buscarPorId(99L));
    }

    @Test
    void buscarPorId_inactivo_lanzaExcepcion() {
        TallerEntity entity = new TallerEntity();
        entity.setId(1L);
        entity.setEstado("INACTIVO");
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        assertThrows(IllegalArgumentException.class, () -> service.buscarPorId(1L));
    }

    @Test
    void eliminar_noExiste_lanzaExcepcion() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(TallerNotFoundException.class, () -> service.eliminar(99L));
    }
}