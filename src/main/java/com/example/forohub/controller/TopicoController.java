package com.example.forohub.controller;

import com.example.forohub.dto.TopicoRequest;
import com.example.forohub.dto.TopicoResponse;
import com.example.forohub.entity.Topico;
import com.example.forohub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService service;

    public TopicoController(TopicoService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<TopicoResponse> crear(@RequestBody @Valid TopicoRequest request) {
        Topico t = service.crearTopico(request);
        return ResponseEntity.ok(new TopicoResponse(
                t.getId(), t.getTitulo(), t.getMensaje(), t.getAutor(), t.getCurso(),
                t.getFechaCreacion().toString(), t.getEstado()
        ));
    }

    @GetMapping
    public List<Topico> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Topico obtener(@PathVariable Long id) { return service.obtener(id); }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public Topico actualizar(@PathVariable Long id, @RequestBody @Valid TopicoRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
