package pe.edu.upeu.msreserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.msreserva.entity.ReservaEntity;
import java.util.List;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
    List<ReservaEntity> findByCodCli(String codCli);
}