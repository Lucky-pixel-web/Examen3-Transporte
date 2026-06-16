package pe.edu.upeu.msreserva.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-cliente", fallback = ClienteClientFallback.class)
public interface ClienteClient {
    @GetMapping("/api/clientes/{codCli}")
    Object buscarPorCodigo(@PathVariable String codCli);
}