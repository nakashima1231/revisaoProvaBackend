package com.example.springloja.dto;

import com.example.springloja.entities.Vendedor;

public record VendedorDTO(
        String nome,
        String foto
) {
    public VendedorDTO(Vendedor v) {
        this(v.getNome(), v.getFoto());
    }
}
