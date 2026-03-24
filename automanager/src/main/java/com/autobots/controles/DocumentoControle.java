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

import com.autobots.mappers.DocumentoMapper;
import com.autobots.modelos.dto.DocumentoDTO;
import com.autobots.modelos.entidades.Documento;
import com.autobots.repositorios.DocumentoRepositorio;
import com.autobots.services.AtualizadorDocumento;
import com.autobots.services.CriadorDocumento;
import com.autobots.services.ExcluidorDocumento;
import com.autobots.services.SelecionadorDocumento;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/documento")
public class DocumentoControle {

    @Autowired
    private DocumentoRepositorio repositorio;
    
    @Autowired
    private SelecionadorDocumento selecionador;
    
    @Autowired
    private CriadorDocumento criador;
    
    @Autowired
    private AtualizadorDocumento atualizador;
    
    @Autowired
    private ExcluidorDocumento excluidor;
    
    @Autowired
    private DocumentoMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoDTO> obterDocumento(@PathVariable long id) {
        Documento documento = selecionador.selecionar(id);
        if (documento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.toDTO(documento));
    }

    @GetMapping
    public ResponseEntity<List<DocumentoDTO>> obterDocumentos() {
        List<Documento> documentos = repositorio.findAll();
        List<DocumentoDTO> dtos = documentos.stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<DocumentoDTO> cadastrarDocumento(@Valid @RequestBody DocumentoDTO documentoDTO) {
        Documento documento = mapper.toEntity(documentoDTO);
        criador.criarDocumento(documento);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(documento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentoDTO> atualizarDocumento(
            @PathVariable long id,
            @Valid @RequestBody DocumentoDTO atualizacaoDTO) {
        
        Documento documento = selecionador.selecionar(id);
        if (documento == null) {
            return ResponseEntity.notFound().build();
        }
        
        Documento atualizacao = mapper.toEntity(atualizacaoDTO);
        atualizacao.setId(id);
        atualizador.atualizarDocumento(documento, atualizacao);
        
        return ResponseEntity.ok(mapper.toDTO(documento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDocumento(@PathVariable long id) {
        Documento documento = selecionador.selecionar(id);
        if (documento == null) {
            return ResponseEntity.notFound().build();
        }
        
        excluidor.deletar(documento);
        return ResponseEntity.noContent().build();
    }
}
