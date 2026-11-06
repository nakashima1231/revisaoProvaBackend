package com.example.springloja.dto;

import com.example.springloja.entities.Cliente;
import com.example.springloja.entities.Venda;
import com.example.springloja.entities.Vendedor;

import java.time.LocalDate;

public record VendaDTO(
        LocalDate data,
        Double valor,
        Cliente cliente,
        Vendedor vendedor
) {
    public VendaDTO(Venda v) {
        this(v.getData(), v.getValor(), v.getCliente(), v.getVendedor());
    }

}
