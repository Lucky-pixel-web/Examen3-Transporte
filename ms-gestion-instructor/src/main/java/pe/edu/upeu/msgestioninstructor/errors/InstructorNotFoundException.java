package pe.edu.upeu.msgestioninstructor.errors;

public class InstructorNotFoundException extends RuntimeException {
    public InstructorNotFoundException(Long id) {
        super("No existe el instructor con id: " + id);
    }
}