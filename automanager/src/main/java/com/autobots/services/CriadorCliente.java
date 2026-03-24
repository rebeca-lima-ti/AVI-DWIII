package com.autobots.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autobots.modelos.entidades.Cliente;
import com.autobots.repositorios.ClienteRepositorio;

@Service
public class CriadorCliente {
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Transactional
    public void criarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório");
        }
        clienteRepositorio.save(cliente);
    }
}
