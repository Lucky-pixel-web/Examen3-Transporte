package pe.edu.upeu.msreserva.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.msreserva.dto.ReservaRequest;
import pe.edu.upeu.msreserva.dto.ReservaResponse;
import pe.edu.upeu.msreserva.entity.ReservaEntity;

@Component
public class ReservaMapper {

    public ReservaEntity toEntity(ReservaRequest request) {
        ReservaEntity entity = new ReservaEntity();
        entity.setFechaReser(request.getFechaReser());
        entity.setHoraReser(request.getHoraReser());
        entity.setCodCli(request.getCodCli());
        entity.setIdProg(request.getIdProg());
        entity.setCodDest(request.getCodDest());
        return entity;
    }

    public ReservaResponse toResponse(ReservaEntity entity) {
        return new ReservaResponse(
                entity.getNroReser(),
                entity.getFechaReser(),
                entity.getHoraReser(),
                entity.getCodCli(),
                entity.getIdProg(),
                entity.getCodDest(),
                entity.getEstado()
        );
    }

    public void updateEntity(ReservaEntity entity, ReservaRequest request) {
        entity.setFechaReser(request.getFechaReser());
        entity.setHoraReser(request.getHoraReser());
        entity.setCodCli(request.getCodCli());
        entity.setIdProg(request.getIdProg());
        entity.setCodDest(request.getCodDest());
    }
}