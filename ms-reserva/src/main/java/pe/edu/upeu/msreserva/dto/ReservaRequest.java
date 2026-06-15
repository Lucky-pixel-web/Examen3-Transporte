package pe.edu.upeu.msreserva.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservaRequest {

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fechaReser;

    @NotNull(message = "La hora es obligatoria")
    private LocalTime horaReser;

    @NotBlank(message = "El código de cliente es obligatorio")
    @Size(max = 5)
    private String codCli;

    @NotNull(message = "El id de programación es obligatorio")
    private Integer idProg;

    @NotBlank(message = "El código de destino es obligatorio")
    @Size(max = 4)
    private String codDest;
}