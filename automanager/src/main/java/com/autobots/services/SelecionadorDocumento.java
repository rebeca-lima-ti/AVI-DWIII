package com.autobots.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.modelos.entidades.Documento;
import com.autobots.repositorios.DocumentoRepositorio;

@Service
public class SelecionadorDocumento {

    @Autowired
    private DocumentoRepositorio documentoRepositorio;

    public Documento selecionar(long id) {
        Optional<Documento> documento = documentoRepositorio.findById(id);
        return documento.orElse(null);
    }
}
