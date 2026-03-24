package com.autobots.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.modelos.entidades.Cliente;
import com.autobots.repositorios.ClienteRepositorio;

@Service
public class ExcluidorCliente {
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public void deletar(Cliente cliente) {
        if (cliente != null && cliente.getId() != null) {
            clienteRepositorio.deleteById(cliente.getId());
        }
    }
}
