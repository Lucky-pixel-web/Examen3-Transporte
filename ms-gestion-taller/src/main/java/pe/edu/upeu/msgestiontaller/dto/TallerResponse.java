package pe.edu.upeu.msgestiontaller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TallerResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private Long instructorId;
    private String nombreInstructor;
    private Long alumnoId;
    private String nombreAlumno;
    private String estado;
}