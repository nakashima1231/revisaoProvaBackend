package com.example.springloja.controller;

import com.example.springloja.dto.VendedorDTO;
import com.example.springloja.entities.Vendedor;
import com.example.springloja.services.VendedorService;
import com.example.springloja.services.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/Vendedors")
@CrossOrigin
@Tag(name="Vendedors", description = "Gerenciamento de estoque de Vendedors")
public class VendedorController {
    @Autowired
    private VendedorService service;

    @Autowired
    private StorageService storageService;

    @GetMapping
    @Operation(summary = "Lista todos os Vendedors", description = "Retorna uma lista completa de Vendedors com suas respectivas categorias")
    public List<VendedorDTO> listar() {
        return service.listarTodas();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "bearerAuth")
    public String criar(@Valid @RequestPart("Vendedor") Vendedor Vendedor,
                        @RequestPart(value = "foto", required = false) MultipartFile foto,
                        @RequestPart(value = "manual", required = false) MultipartFile manual) {

        if (foto != null && !foto.isEmpty()) {
            String caminho = storageService.salvarArquivo(foto);
            Vendedor.setFoto(caminho);
        }

        service.salvar(Vendedor);
        return "Vendedor " + Vendedor.getNome() + " criado com sucesso!";
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "bearerAuth")
    public String atualizar(
            @PathVariable Long id,
            @Valid @RequestPart("Vendedor") Vendedor Vendedor,
            @RequestPart(value = "foto", required = false) MultipartFile foto,
            @RequestPart(value = "manual", required = false) MultipartFile manual) {

        service.atualizar(id, Vendedor, foto, manual);
        return "Vendedor atualizado com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id){
        service.excluir(id);
        return "Vendedor deletado com sucesso!";
    }

    @GetMapping("/filtro-nome")
    public List<VendedorDTO> filtrarPorNome(@RequestParam String nome) {
        return service.buscarPorNome(nome);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendedorDTO> buscarPorId(@PathVariable Long id){
        VendedorDTO obj = service.buscarPorId(id);

        if (obj != null){
            return ResponseEntity.ok().body(obj);
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
