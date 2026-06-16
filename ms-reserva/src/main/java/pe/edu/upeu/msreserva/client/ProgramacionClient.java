package pe.edu.upeu.msreserva.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-programacion", fallback = ProgramacionClientFallback.class)
public interface ProgramacionClient {
    @GetMapping("/api/programaciones/{idProg}")
    Object buscarPorId(@PathVariable Integer idProg);
}