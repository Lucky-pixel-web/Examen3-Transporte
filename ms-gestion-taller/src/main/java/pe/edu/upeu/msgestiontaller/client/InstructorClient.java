package pe.edu.upeu.msgestiontaller.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.upeu.msgestiontaller.dto.InstructorResponse;

@FeignClient(name = "ms-gestion-instructor")
public interface InstructorClient {
    @GetMapping("/api/instructores/{id}")
    InstructorResponse buscarPorId(@PathVariable Long id);
}