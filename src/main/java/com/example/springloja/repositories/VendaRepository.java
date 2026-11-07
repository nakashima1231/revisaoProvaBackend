package com.example.springloja.repositories;

import com.example.springloja.dto.VendaDTO;
import com.example.springloja.entities.Cliente;
import com.example.springloja.entities.Venda;
import com.example.springloja.entities.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findByData(LocalDate data);
    List<Venda> findByCliente_nomeIgnoringCase(String nome);
    List<Venda> findByVendedor_NomeIgnoringCase(String nome);
}