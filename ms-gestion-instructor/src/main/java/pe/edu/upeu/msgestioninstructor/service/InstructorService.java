package pe.edu.upeu.msgestioninstructor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.msgestioninstructor.dto.InstructorRequest;
import pe.edu.upeu.msgestioninstructor.dto.InstructorResponse;
import pe.edu.upeu.msgestioninstructor.entity.InstructorEntity;
import pe.edu.upeu.msgestioninstructor.errors.InstructorNotFoundException;
import pe.edu.upeu.msgestioninstructor.mappers.InstructorMapper;
import pe.edu.upeu.msgestioninstructor.repository.InstructorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository repository;
    private final InstructorMapper mapper;

    public List<InstructorResponse> listar() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public InstructorResponse buscarPorId(Long id) {
        InstructorEntity entity = repository.findById(id)
                .orElseThrow(() -> new InstructorNotFoundException(id));
        if ("INACTIVO".equals(entity.getEstado())) {
            throw new IllegalArgumentException("El instructor con id " + id + " está INACTIVO y no puede ser consultado.");
        }
        return mapper.toResponse(entity);
    }

    public InstructorResponse crear(InstructorRequest request) {
        repository.findByDni(request.getDni()).ifPresent(i -> {
            throw new IllegalArgumentException("Ya existe un instructor con DNI: " + request.getDni());
        });
        InstructorEntity entity = mapper.toEntity(request);
        entity.setEstado("ACTIVO");
        return mapper.toResponse(repository.save(entity));
    }

    public InstructorResponse actualizar(Long id, InstructorRequest request) {
        InstructorEntity entity = repository.findById(id)
                .orElseThrow(() -> new InstructorNotFoundException(id));
        if ("INACTIVO".equals(entity.getEstado())) {
            throw new IllegalArgumentException("El instructor con id " + id + " está INACTIVO y no puede ser modificado.");
        }
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    public void eliminar(Long id) {
        InstructorEntity entity = repository.findById(id)
                .orElseThrow(() -> new InstructorNotFoundException(id));
        entity.setEstado("INACTIVO");
        repository.save(entity);
    }
    public List<InstructorResponse> buscarPorNombre(String nombre) {
        List<InstructorEntity> resultado = repository.findByNombreContainingIgnoreCase(nombre);
        if (resultado.isEmpty()) {
            throw new IllegalArgumentException("No se encontró ningún instructor con el nombre: '" + nombre + "'");
        }
        return resultado.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}