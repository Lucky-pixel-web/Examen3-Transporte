package pe.edu.upeu.msgestioninstructor.mappers;

import org.springframework.stereotype.Component;
import pe.edu.upeu.msgestioninstructor.dto.InstructorRequest;
import pe.edu.upeu.msgestioninstructor.dto.InstructorResponse;
import pe.edu.upeu.msgestioninstructor.entity.InstructorEntity;

@Component
public class InstructorMapper {

    public InstructorEntity toEntity(InstructorRequest request) {
        InstructorEntity entity = new InstructorEntity();
        entity.setNombre(request.getNombre());
        entity.setApellido(request.getApellido());
        entity.setDni(request.getDni());
        entity.setCorreo(request.getCorreo());
        entity.setEspecialidad(request.getEspecialidad());
        return entity;
    }

    public InstructorResponse toResponse(InstructorEntity entity) {
        return new InstructorResponse(
                entity.getId(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getDni(),
                entity.getCorreo(),
                entity.getEspecialidad()
        );
    }

    public void updateEntity(InstructorEntity entity, InstructorRequest request) {
        entity.setNombre(request.getNombre());
        entity.setApellido(request.getApellido());
        entity.setDni(request.getDni());
        entity.setCorreo(request.getCorreo());
        entity.setEspecialidad(request.getEspecialidad());
    }
}