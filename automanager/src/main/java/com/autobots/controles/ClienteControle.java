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

import com.autobots.mappers.ClienteMapper;
import com.autobots.modelos.dto.ClienteDTO;
import com.autobots.modelos.entidades.Cliente;
import com.autobots.repositorios.ClienteRepositorio;
import com.autobots.services.AtualizadorCliente;
import com.autobots.services.CriadorCliente;
import com.autobots.services.ExcluidorCliente;
import com.autobots.services.SelecionadorCliente;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteControle {

    @Autowired
    private ClienteRepositorio repositorio;
    
    @Autowired
    private SelecionadorCliente selecionador;
    
    @Autowired
    private CriadorCliente criador;
    
    @Autowired
    private AtualizadorCliente atualizador;
    
    @Autowired
    private ExcluidorCliente excluidor;
    
    @Autowired
    private ClienteMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obterCliente(@PathVariable long id) {
        Cliente cliente = selecionador.selecionar(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.toDTO(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obterClientes() {
        List<Cliente> clientes = repositorio.findAll();
        List<ClienteDTO> dtos = clientes.stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = mapper.toEntity(clienteDTO);
        criador.criarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(
            @PathVariable long id,
            @Valid @RequestBody ClienteDTO atualizacaoDTO) {
        
        Cliente cliente = selecionador.selecionar(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        
        Cliente atualizacao = mapper.toEntity(atualizacaoDTO);
        atualizacao.setId(id);
        atualizador.atualizar(cliente, atualizacao);
        
        return ResponseEntity.ok(mapper.toDTO(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable long id) {
        Cliente cliente = selecionador.selecionar(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        
        excluidor.deletar(cliente);
        return ResponseEntity.noContent().build();
    }
}
