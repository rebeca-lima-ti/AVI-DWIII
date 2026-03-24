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

import com.autobots.mappers.TelefoneMapper;
import com.autobots.modelos.dto.TelefoneDTO;
import com.autobots.modelos.entidades.Telefone;
import com.autobots.repositorios.TelefoneRepositorio;
import com.autobots.services.AtualizadorTelefone;
import com.autobots.services.CriadorTelefone;
import com.autobots.services.ExcluidorTelefone;
import com.autobots.services.SelecionadorTelefone;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {

    @Autowired
    private TelefoneRepositorio repositorio;
    
    @Autowired
    private SelecionadorTelefone selecionador;
    
    @Autowired
    private CriadorTelefone criador;
    
    @Autowired
    private AtualizadorTelefone atualizador;
    
    @Autowired
    private ExcluidorTelefone excluidor;
    
    @Autowired
    private TelefoneMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<TelefoneDTO> obterTelefone(@PathVariable long id) {
        Telefone telefone = selecionador.selecionar(id);
        if (telefone == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.toDTO(telefone));
    }

    @GetMapping
    public ResponseEntity<List<TelefoneDTO>> obterTelefones() {
        List<Telefone> telefones = repositorio.findAll();
        List<TelefoneDTO> dtos = telefones.stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<TelefoneDTO> cadastrarTelefone(@Valid @RequestBody TelefoneDTO telefoneDTO) {
        Telefone telefone = mapper.toEntity(telefoneDTO);
        criador.criarTelefone(telefone);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(telefone));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelefoneDTO> atualizarTelefone(
            @PathVariable long id,
            @Valid @RequestBody TelefoneDTO atualizacaoDTO) {
        
        Telefone telefone = selecionador.selecionar(id);
        if (telefone == null) {
            return ResponseEntity.notFound().build();
        }
        
        Telefone atualizacao = mapper.toEntity(atualizacaoDTO);
        atualizacao.setId(id);
        atualizador.atualizarTelefone(telefone, atualizacao);
        
        return ResponseEntity.ok(mapper.toDTO(telefone));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTelefone(@PathVariable long id) {
        Telefone telefone = selecionador.selecionar(id);
        if (telefone == null) {
            return ResponseEntity.notFound().build();
        }
        
        excluidor.deletar(telefone);
        return ResponseEntity.noContent().build();
    }
}
