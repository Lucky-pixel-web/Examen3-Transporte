package pe.edu.upeu.msgestiontaller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.msgestiontaller.entity.TallerEntity;

import java.util.List;

public interface TallerRepository extends JpaRepository<TallerEntity, Long> {
    List<TallerEntity> findByNombreContainingIgnoreCase(String nombre);
}