package pe.edu.upeu.msgestiontaller.dto;

import lombok.Data;

@Data
public class AlumnoResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
}