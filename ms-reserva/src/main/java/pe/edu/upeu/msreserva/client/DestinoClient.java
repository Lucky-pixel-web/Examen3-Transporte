package pe.edu.upeu.msreserva.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-destino", fallback = DestinoClientFallback.class)
public interface DestinoClient {
    @GetMapping("/api/destinos/{codDest}")
    Object buscarPorCodigo(@PathVariable String codDest);
}