package pe.edu.upeu.msreserva.client;

import org.springframework.stereotype.Component;

@Component
public class DestinoClientFallback implements DestinoClient {
    @Override
    public Object buscarPorCodigo(String codDest) {
        return null;
    }
}