package com.autobots.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.modelos.entidades.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
}
