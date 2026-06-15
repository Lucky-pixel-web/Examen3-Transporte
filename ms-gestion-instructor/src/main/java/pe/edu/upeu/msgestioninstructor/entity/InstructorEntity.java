package pe.edu.upeu.msgestioninstructor.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "instructores")
public class InstructorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, unique = true, length = 8)
    private String dni;

    @Column(nullable = false, length = 100)
    private String correo;

    @Column(nullable = false, length = 100)
    private String especialidad;

    @Column(nullable = false, length = 20)
    private String estado;
}