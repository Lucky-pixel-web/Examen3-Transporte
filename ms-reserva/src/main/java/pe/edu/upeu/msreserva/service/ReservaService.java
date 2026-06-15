package pe.edu.upeu.msreserva.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.msreserva.dto.ReservaRequest;
import pe.edu.upeu.msreserva.dto.ReservaResponse;
import pe.edu.upeu.msreserva.entity.ReservaEntity;
import pe.edu.upeu.msreserva.errors.ReservaNotFoundException;
import pe.edu.upeu.msreserva.mapper.ReservaMapper;
import pe.edu.upeu.msreserva.repository.ReservaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository repository;
    private final ReservaMapper mapper;

    public List<ReservaResponse> listar() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public ReservaResponse buscarPorId(Long id) {
        ReservaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ReservaNotFoundException(id));
        if ("INACTIVO".equals(entity.getEstado())) {
            throw new IllegalArgumentException("La reserva con nro " + id + " está INACTIVA y no puede ser consultada.");
        }
        return mapper.toResponse(entity);
    }

    public ReservaResponse crear(ReservaRequest request) {
        ReservaEntity entity = mapper.toEntity(request);
        entity.setEstado("ACTIVO");
        return mapper.toResponse(repository.save(entity));
    }

    public ReservaResponse actualizar(Long id, ReservaRequest request) {
        ReservaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ReservaNotFoundException(id));
        if ("INACTIVO".equals(entity.getEstado())) {
            throw new IllegalArgumentException("La reserva con nro " + id + " está INACTIVA y no puede ser modificada.");
        }
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    public void eliminar(Long id) {
        ReservaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ReservaNotFoundException(id));
        entity.setEstado("INACTIVO");
        repository.save(entity);
    }
}