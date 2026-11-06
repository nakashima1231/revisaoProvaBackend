package com.example.springloja.services;

import com.example.springloja.dto.ClienteDTO;
import com.example.springloja.dto.ProdutoDTO;
import com.example.springloja.dto.VendaDTO;
import com.example.springloja.entities.Cliente;
import com.example.springloja.entities.Venda;
import com.example.springloja.entities.Produto;
import com.example.springloja.entities.Vendedor;
import com.example.springloja.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository repository;

    public List<VendaDTO> listarTodas() {
        return repository.findAll().stream().map(VendaDTO::new).toList();
    }

    public void salvar(Venda Venda) {
        repository.save(Venda);
    }

    public void atualizar(Venda Venda){
        repository.save(Venda);
    }

    public void excluir(Long id){
        repository.deleteById(id);
    }

    public Venda buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<VendaDTO> buscarPorData(LocalDate data) {
        return repository.findByDataGreaterThan(data).stream().map(VendaDTO::new).toList();
    }

    public List<VendaDTO> buscarPorCliente(Cliente cliente) {
        return repository.findByCliente_nomeIgnoringCase(cliente).stream().map(VendaDTO::new).toList();
    }

    public List<VendaDTO> buscarPorVendedor(Vendedor vendedor) {
        return repository.findByVendedor_NomeIgnoringCase(vendedor).stream().map(VendaDTO::new).toList();
    }
}


