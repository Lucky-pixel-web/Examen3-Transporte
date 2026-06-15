package pe.edu.upeu.msgestiontaller.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msgestiontaller.dto.TallerRequest;
import pe.edu.upeu.msgestiontaller.dto.TallerResponse;
import pe.edu.upeu.msgestiontaller.service.TallerService;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/api/talleres")
@RequiredArgsConstructor
public class TallerController {

    private final TallerService service;

    @GetMapping
    public ResponseEntity<List<TallerResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TallerResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<TallerResponse>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(service.buscarPorNombre(nombre));
    }

    @PostMapping
    public ResponseEntity<TallerResponse> crear(@Valid @RequestBody TallerRequest request) {
        return new ResponseEntity<>(service.crear(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TallerResponse> actualizar(@PathVariable Long id,
                                                     @Valid @RequestBody TallerRequest request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Taller eliminado exitosamente. Verifique en el listado.");
        response.put("estado", "INACTIVO");
        return ResponseEntity.ok(response);
    }
}