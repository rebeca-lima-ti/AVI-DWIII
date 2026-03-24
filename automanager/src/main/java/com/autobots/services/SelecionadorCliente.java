package com.autobots.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.modelos.entidades.Cliente;
import com.autobots.repositorios.ClienteRepositorio;

@Service
public class SelecionadorCliente {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public Cliente selecionar(long id) {
        Optional<Cliente> cliente = clienteRepositorio.findById(id);
        return cliente.orElse(null);
    }
}
