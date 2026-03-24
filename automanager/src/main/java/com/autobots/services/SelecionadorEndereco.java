package com.autobots.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.modelos.entidades.Endereco;
import com.autobots.repositorios.EnderecoRepositorio;

@Service
public class SelecionadorEndereco {

    @Autowired
    private EnderecoRepositorio enderecoRepositorio;

    public Endereco selecionar(long id) {
        Optional<Endereco> endereco = enderecoRepositorio.findById(id);
        return endereco.orElse(null);
    }
}
