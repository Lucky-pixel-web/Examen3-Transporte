package pe.edu.upeu.msgestiontaller.errors;

public class TallerNotFoundException extends RuntimeException {
    public TallerNotFoundException(Long id) {
        super("No existe el taller con id: " + id);
    }
}