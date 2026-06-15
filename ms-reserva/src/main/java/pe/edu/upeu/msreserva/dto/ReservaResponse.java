package pe.edu.upeu.msreserva.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaResponse {
    private Long nroReser;
    private LocalDate fechaReser;
    private LocalTime horaReser;
    private String codCli;
    private Integer idProg;
    private String codDest;
    private String estado;
}