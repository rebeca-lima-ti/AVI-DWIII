package com.autobots.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.modelos.entidades.Documento;

public interface DocumentoRepositorio extends JpaRepository<Documento, Long> {
    
}
