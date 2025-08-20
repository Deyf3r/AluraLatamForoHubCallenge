package com.example.forohub.dto;

public record TopicoResponse(
        Long id,
        String titulo,
        String mensaje,
        String autor,
        String curso,
        String fechaCreacion,
        Boolean estado
) {}
