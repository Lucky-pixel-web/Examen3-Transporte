package pe.edu.upeu.msgestiontaller.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TallerRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 200)
    private String descripcion;

    @NotNull(message = "El instructor es obligatorio")
    private Long instructorId;

    @NotNull(message = "El alumno es obligatorio")
    private Long alumnoId;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;
}