package pe.edu.upeu.msreserva.errors;

public class ReservaNotFoundException extends RuntimeException {
    public ReservaNotFoundException(Long id) {
        super("No existe la reserva con nro: " + id);
    }
}