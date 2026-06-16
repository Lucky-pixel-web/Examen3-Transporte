package pe.edu.upeu.msreserva.client;

import org.springframework.stereotype.Component;

@Component
public class ProgramacionClientFallback implements ProgramacionClient {
    @Override
    public Object buscarPorId(Integer idProg) {
        return null;
    }
}