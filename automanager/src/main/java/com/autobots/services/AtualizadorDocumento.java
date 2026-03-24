package com.autobots.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autobots.modelos.entidades.Documento;
import com.autobots.repositorios.DocumentoRepositorio;

@Service
public class AtualizadorDocumento {

    @Autowired
    private DocumentoRepositorio documentoRepositorio;
    
    @Autowired
    private VerificadorStringNulo verificador;

    @Transactional
    public void atualizarDocumento(Documento documento, Documento atualizacao) {
        if (documento == null || atualizacao == null) {
            return;
        }
        
        if (!verificador.verificar(atualizacao.getTipo())) {
            documento.setTipo(atualizacao.getTipo());
        }
        if (!verificador.verificar(atualizacao.getNumero())) {
            documento.setNumero(atualizacao.getNumero());
        }
        documentoRepositorio.save(documento);
    }

    @Transactional
    public void atualizarDocumento(List<Documento> documentos, List<Documento> atualizacoes) {
        if (documentos == null || atualizacoes == null || atualizacoes.isEmpty()) {
            return;
        }
        
        for (Documento atualizacao : atualizacoes) {
            if (atualizacao.getId() != null) {
                // Use find em vez de loop O(n²)
                Optional<Documento> documentoOpt = documentos.stream()
                    .filter(d -> d != null && d.getId().equals(atualizacao.getId()))
                    .findFirst();
                
                if (documentoOpt.isPresent()) {
                    Documento documento = documentoOpt.get();
                    atualizarDocumento(documento, atualizacao);
                }
            }
        }
    }
}
