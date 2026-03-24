package com.autobots.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.modelos.entidades.Endereco;

public interface EnderecoRepositorio extends JpaRepository<Endereco, Long> {
    
}
