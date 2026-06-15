package pe.edu.upeu.msgestiontaller.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "talleres")
public class TallerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 200)
    private String descripcion;

    @Column(nullable = false)
    private Long instructorId;

    @Column(nullable = false)
    private Long alumnoId;

    @Column(nullable = false, length = 50)
    private String estado;
}