package com.autobots.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.modelos.entidades.Documento;
import com.autobots.repositorios.DocumentoRepositorio;

@Service
public class ExcluidorDocumento {
    
    @Autowired
    private DocumentoRepositorio documentoRepositorio;

    public void deletar(Documento documento) {
        if (documento != null && documento.getId() != null) {
            documentoRepositorio.deleteById(documento.getId());
        }
    }
}
