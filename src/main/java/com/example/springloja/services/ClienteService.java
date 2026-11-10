package com.example.springloja.services;

import com.example.springloja.dto.ClienteDTO;
import com.example.springloja.dto.ProdutoDTO;
import com.example.springloja.entities.Cliente;
import com.example.springloja.entities.Produto;
import com.example.springloja.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<ClienteDTO> listarTodas() {
        return repository.findAll().stream().map(ClienteDTO::new).toList();
    }

    public void salvar(Cliente Cliente) {
        repository.save(Cliente);
    }

    public void atualizar(Cliente Cliente){
        repository.save(Cliente);
    }

    public void excluir(Long id){
        repository.deleteById(id);
    }


    public ClienteDTO buscarPorId(Long id){
        return repository.findById(id).map(ClienteDTO:: new).orElse(null);
    }

    public List<ClienteDTO> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome).stream().map(ClienteDTO::new).toList();
    }
}


