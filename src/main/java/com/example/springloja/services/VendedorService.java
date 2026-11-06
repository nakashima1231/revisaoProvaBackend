package com.example.springloja.services;

import com.example.springloja.dto.VendedorDTO;
import com.example.springloja.dto.VendedorDTO;
import com.example.springloja.entities.Vendedor;
import com.example.springloja.entities.Vendedor;
import com.example.springloja.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository repository;

    @Autowired
    private StorageService storageService;

    public List<VendedorDTO> listarTodas() {
        return repository.findAll().stream().map(VendedorDTO::new).toList();
    }

    public void salvar(Vendedor Vendedor) {
        repository.save(Vendedor);
    }

    @Transactional
    public Vendedor atualizar(Long id, Vendedor dadosAtualizados, MultipartFile novaFoto, MultipartFile novoManual) {
        Vendedor VendedorExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado com id: " + id));

        // Atualiza os campos normais
        VendedorExistente.setNome(dadosAtualizados.getNome());

        if (novaFoto != null && !novaFoto.isEmpty()) {
            if (VendedorExistente.getFoto() != null) {
                storageService.deletarArquivo(VendedorExistente.getFoto());
            }

            String novoCaminho = storageService.salvarArquivo(novaFoto);
            VendedorExistente.setFoto(novoCaminho);
        }

        return repository.save(VendedorExistente);
    }

    public void excluir(Long id){
        repository.deleteById(id);
    }

    public VendedorDTO buscarPorId(Long id){
        return repository.findById(id).stream().map(VendedorDTO::new).findFirst().orElse(null);
    }

    public List<VendedorDTO> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome).stream().map(VendedorDTO::new).toList();
    }

}


