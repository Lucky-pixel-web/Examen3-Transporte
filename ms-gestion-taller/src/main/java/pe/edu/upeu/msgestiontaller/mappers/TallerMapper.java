package pe.edu.upeu.msgestiontaller.mappers;

import org.springframework.stereotype.Component;
import pe.edu.upeu.msgestiontaller.dto.TallerRequest;
import pe.edu.upeu.msgestiontaller.dto.TallerResponse;
import pe.edu.upeu.msgestiontaller.entity.TallerEntity;

@Component
public class TallerMapper {

    public TallerEntity toEntity(TallerRequest request) {
        TallerEntity entity = new TallerEntity();
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        entity.setInstructorId(request.getInstructorId());
        entity.setAlumnoId(request.getAlumnoId());
        entity.setEstado(request.getEstado());
        return entity;
    }

    public TallerResponse toResponse(TallerEntity entity) {
        TallerResponse response = new TallerResponse();
        response.setId(entity.getId());
        response.setNombre(entity.getNombre());
        response.setDescripcion(entity.getDescripcion());
        response.setInstructorId(entity.getInstructorId());
        response.setAlumnoId(entity.getAlumnoId());
        response.setEstado(entity.getEstado());
        return response;
    }

    public void updateEntity(TallerEntity entity, TallerRequest request) {
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        entity.setInstructorId(request.getInstructorId());
        entity.setAlumnoId(request.getAlumnoId());
        entity.setEstado(request.getEstado());
    }
}