package com.example.springloja.dto;

import com.example.springloja.entities.Cliente;
import com.example.springloja.entities.Venda;
import com.example.springloja.entities.Vendedor;

import java.time.LocalDate;

public record VendaDTO(
        Long id,
        LocalDate data,
        Double valor,
        ClienteDTO cliente,
        VendedorDTO vendedor
) {
    public VendaDTO(Venda v) {
        this(v.getId(), v.getData(), v.getValor(), new ClienteDTO(v.getCliente()), new VendedorDTO(v.getVendedor()));
    }

}
