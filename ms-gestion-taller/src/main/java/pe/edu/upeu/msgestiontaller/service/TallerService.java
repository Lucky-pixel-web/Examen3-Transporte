package pe.edu.upeu.msgestiontaller.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.msgestiontaller.client.AlumnoClient;
import pe.edu.upeu.msgestiontaller.client.InstructorClient;
import pe.edu.upeu.msgestiontaller.dto.TallerRequest;
import pe.edu.upeu.msgestiontaller.dto.TallerResponse;
import pe.edu.upeu.msgestiontaller.entity.TallerEntity;
import pe.edu.upeu.msgestiontaller.errors.TallerNotFoundException;
import pe.edu.upeu.msgestiontaller.mappers.TallerMapper;
import pe.edu.upeu.msgestiontaller.repository.TallerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TallerService {

    private final TallerRepository repository;
    private final TallerMapper mapper;
    private final InstructorClient instructorClient;
    private final AlumnoClient alumnoClient;

    public List<TallerResponse> listar() {
        return repository.findAll().stream()
                .map(this::enriquecer)
                .collect(Collectors.toList());
    }

    public TallerResponse buscarPorId(Long id) {
        TallerEntity entity = repository.findById(id)
                .orElseThrow(() -> new TallerNotFoundException(id));
        if ("INACTIVO".equals(entity.getEstado())) {
            throw new IllegalArgumentException("El taller con id " + id + " está INACTIVO y no puede ser consultado.");
        }
        return enriquecer(entity);
    }

    public TallerResponse crear(TallerRequest request) {
        TallerEntity entity = mapper.toEntity(request);
        return enriquecer(repository.save(entity));
    }

    public TallerResponse actualizar(Long id, TallerRequest request) {
        TallerEntity entity = repository.findById(id)
                .orElseThrow(() -> new TallerNotFoundException(id));
        if ("INACTIVO".equals(entity.getEstado())) {
            throw new IllegalArgumentException("El taller con id " + id + " está INACTIVO y no puede ser modificado.");
        }
        mapper.updateEntity(entity, request);
        return enriquecer(repository.save(entity));
    }

    public void eliminar(Long id) {
        TallerEntity entity = repository.findById(id)
                .orElseThrow(() -> new TallerNotFoundException(id));
        entity.setEstado("INACTIVO");
        repository.save(entity);
    }

    public List<TallerResponse> buscarPorNombre(String nombre) {
        List<TallerEntity> resultado = repository.findByNombreContainingIgnoreCase(nombre);
        if (resultado.isEmpty()) {
            throw new IllegalArgumentException("No se encontró ningún taller con el nombre: '" + nombre + "'");
        }
        return resultado.stream()
                .map(this::enriquecer)
                .collect(Collectors.toList());
    }

    private TallerResponse enriquecer(TallerEntity entity) {
        TallerResponse response = mapper.toResponse(entity);
        try {
            var instructor = instructorClient.buscarPorId(entity.getInstructorId());
            response.setNombreInstructor(instructor.getNombre() + " " + instructor.getApellido());
        } catch (Exception e) {
            response.setNombreInstructor("No disponible");
        }
        try {
            var alumno = alumnoClient.buscarPorId(entity.getAlumnoId());
            response.setNombreAlumno(alumno.getNombre() + " " + alumno.getApellido());
        } catch (Exception e) {
            response.setNombreAlumno("No disponible");
        }
        return response;
    }
}