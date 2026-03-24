package com.autobots.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.modelos.entidades.Telefone;

public interface TelefoneRepositorio extends JpaRepository<Telefone, Long>{
    
}
