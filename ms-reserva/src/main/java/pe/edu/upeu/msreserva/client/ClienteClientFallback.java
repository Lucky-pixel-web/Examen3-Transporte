package pe.edu.upeu.msreserva.client;

import org.springframework.stereotype.Component;

@Component
public class ClienteClientFallback implements ClienteClient {
    @Override
    public Object buscarPorCodigo(String codCli) {
        return null;
    }
}