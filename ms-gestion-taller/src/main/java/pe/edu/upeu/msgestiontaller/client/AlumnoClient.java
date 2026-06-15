package pe.edu.upeu.msgestiontaller.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.upeu.msgestiontaller.dto.AlumnoResponse;

@FeignClient(name = "ms-gestion-alumno")
public interface AlumnoClient {
    @GetMapping("/api/alumnos/{id}")
    AlumnoResponse buscarPorId(@PathVariable Long id);
}