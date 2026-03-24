package com.autobots.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autobots.modelos.entidades.Documento;
import com.autobots.repositorios.DocumentoRepositorio;

@Service
public class CriadorDocumento {
    @Autowired
    private DocumentoRepositorio documentoRepositorio;

    @Transactional
    public void criarDocumento(Documento documento) {
        if (documento == null) {
            throw new IllegalArgumentException("Documento não pode ser nulo");
        }
        if (documento.getTipo() == null || documento.getTipo().isBlank()) {
            throw new IllegalArgumentException("Tipo do documento é obrigatório");
        }
        if (documento.getNumero() == null || documento.getNumero().isBlank()) {
            throw new IllegalArgumentException("Número do documento é obrigatório");
        }
        documentoRepositorio.save(documento);
    }
}
