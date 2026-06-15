package pe.edu.upeu.msgestiontaller.dto;

import lombok.Data;

@Data
public class InstructorResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String especialidad;
}