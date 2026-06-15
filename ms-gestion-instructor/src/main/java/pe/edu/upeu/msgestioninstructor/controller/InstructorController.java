package pe.edu.upeu.msgestioninstructor.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msgestioninstructor.dto.InstructorRequest;
import pe.edu.upeu.msgestioninstructor.dto.InstructorResponse;
import pe.edu.upeu.msgestioninstructor.service.InstructorService;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/api/instructores")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService service;

    @GetMapping
    public ResponseEntity<List<InstructorResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<InstructorResponse>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(service.buscarPorNombre(nombre));
    }

    @PostMapping
    public ResponseEntity<InstructorResponse> crear(@Valid @RequestBody InstructorRequest request) {
        return new ResponseEntity<>(service.crear(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstructorResponse> actualizar(@PathVariable Long id,
                                                         @Valid @RequestBody InstructorRequest request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Instructor eliminado exitosamente. Verifique en el listado.");
        response.put("estado", "INACTIVO");
        return ResponseEntity.ok(response);
    }
}