package pe.edu.upeu.msreserva.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "reserva")
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nroReser;

    @Column(nullable = false)
    private LocalDate fechaReser;

    @Column(nullable = false)
    private LocalTime horaReser;

    @Column(nullable = false, length = 5)
    private String codCli;

    @Column(nullable = false)
    private Integer idProg;

    @Column(nullable = false, length = 4)
    private String codDest;

    @Column(nullable = false, length = 20)
    private String estado;
}