package com.autobots.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.modelos.entidades.Endereco;
import com.autobots.repositorios.EnderecoRepositorio;

@Service
public class ExcluidorEndereco {

    @Autowired
    private EnderecoRepositorio enderecoRepositorio;

    public void deletar(Endereco endereco) {
        if (endereco != null && endereco.getId() != null) {
            enderecoRepositorio.deleteById(endereco.getId());
        }
    }


}
