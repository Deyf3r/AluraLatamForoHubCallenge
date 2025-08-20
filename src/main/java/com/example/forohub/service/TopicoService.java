package com.example.forohub.service;

import com.example.forohub.dto.TopicoRequest;
import com.example.forohub.entity.Topico;
import com.example.forohub.repository.TopicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopicoService {
    private final TopicoRepository repository;

    public TopicoService(TopicoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Topico crearTopico(TopicoRequest request) {
        repository.findByTituloAndMensaje(request.titulo(), request.mensaje())
                .ifPresent(t -> { throw new IllegalArgumentException("Tópico duplicado"); });

        Topico t = new Topico();
        t.setTitulo(request.titulo());
        t.setMensaje(request.mensaje());
        t.setAutor(request.autor());
        t.setCurso(request.curso());
        return repository.save(t);
    }

    public List<Topico> listar() {
        return repository.findAll();
    }

    public Topico obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
    }

    @Transactional
    public Topico actualizar(Long id, TopicoRequest request) {
        Topico t = obtener(id);
        t.setTitulo(request.titulo());
        t.setMensaje(request.mensaje());
        t.setAutor(request.autor());
        t.setCurso(request.curso());
        return repository.save(t);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Tópico no encontrado");
        }
        repository.deleteById(id);
    }
}
