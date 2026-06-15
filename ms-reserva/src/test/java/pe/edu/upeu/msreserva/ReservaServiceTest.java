package pe.edu.upeu.msreserva;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upeu.msreserva.entity.ReservaEntity;
import pe.edu.upeu.msreserva.errors.ReservaNotFoundException;
import pe.edu.upeu.msreserva.mapper.ReservaMapper;
import pe.edu.upeu.msreserva.repository.ReservaRepository;
import pe.edu.upeu.msreserva.service.ReservaService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @Mock
    private ReservaRepository repository;

    @Mock
    private ReservaMapper mapper;

    @InjectMocks
    private ReservaService service;

    @Test
    void buscarPorId_noExiste_lanzaExcepcion() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ReservaNotFoundException.class, () -> service.buscarPorId(99L));
    }

    @Test
    void buscarPorId_inactivo_lanzaExcepcion() {
        ReservaEntity entity = new ReservaEntity();
        entity.setNroReser(1L);
        entity.setEstado("INACTIVO");
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        assertThrows(IllegalArgumentException.class, () -> service.buscarPorId(1L));
    }

    @Test
    void eliminar_noExiste_lanzaExcepcion() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ReservaNotFoundException.class, () -> service.eliminar(99L));
    }
}