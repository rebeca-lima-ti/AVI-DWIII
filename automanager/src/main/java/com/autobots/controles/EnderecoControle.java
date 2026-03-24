package com.autobots.controles;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.mappers.EnderecoMapper;
import com.autobots.modelos.dto.EnderecoDTO;
import com.autobots.modelos.entidades.Endereco;
import com.autobots.repositorios.EnderecoRepositorio;
import com.autobots.services.AtualizadorEndereco;
import com.autobots.services.CriadorEndereco;
import com.autobots.services.ExcluidorEndereco;
import com.autobots.services.SelecionadorEndereco;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/endereco")
public class EnderecoControle {

    @Autowired
    private EnderecoRepositorio repositorio;
    
    @Autowired
    private SelecionadorEndereco selecionador;
    
    @Autowired
    private CriadorEndereco criador;
    
    @Autowired
    private AtualizadorEndereco atualizador;
    
    @Autowired
    private ExcluidorEndereco excluidor;
    
    @Autowired
    private EnderecoMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> obterEndereco(@PathVariable long id) {
        Endereco endereco = selecionador.selecionar(id);
        if (endereco == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.toDTO(endereco));
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> obterEnderecos() {
        List<Endereco> enderecos = repositorio.findAll();
        List<EnderecoDTO> dtos = enderecos.stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(@Valid @RequestBody EnderecoDTO enderecoDTO) {
        Endereco endereco = mapper.toEntity(enderecoDTO);
        criador.criarEndereco(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(endereco));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(
            @PathVariable long id,
            @Valid @RequestBody EnderecoDTO atualizacaoDTO) {
        
        Endereco endereco = selecionador.selecionar(id);
        if (endereco == null) {
            return ResponseEntity.notFound().build();
        }
        
        Endereco atualizacao = mapper.toEntity(atualizacaoDTO);
        atualizacao.setId(id);
        atualizador.atualizarEndereco(endereco, atualizacao);
        
        return ResponseEntity.ok(mapper.toDTO(endereco));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEndereco(@PathVariable long id) {
        Endereco endereco = selecionador.selecionar(id);
        if (endereco == null) {
            return ResponseEntity.notFound().build();
        }
        
        excluidor.deletar(endereco);
        return ResponseEntity.noContent().build();
    }
}
