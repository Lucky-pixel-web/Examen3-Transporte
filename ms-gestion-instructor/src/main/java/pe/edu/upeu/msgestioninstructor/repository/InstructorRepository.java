package pe.edu.upeu.msgestioninstructor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.msgestioninstructor.entity.InstructorEntity;

import java.util.List;
import java.util.Optional;

public interface InstructorRepository extends JpaRepository<InstructorEntity, Long> {
    Optional<InstructorEntity> findByDni(String dni);
    List<InstructorEntity> findByNombreContainingIgnoreCase(String nombre);
    Optional<InstructorEntity> findByNombreIgnoreCase(String nombre); // ← AGREGAR ESTO
}