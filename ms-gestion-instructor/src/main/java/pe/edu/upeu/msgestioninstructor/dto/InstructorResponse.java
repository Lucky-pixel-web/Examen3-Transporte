package pe.edu.upeu.msgestioninstructor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String correo;
    private String especialidad;
}