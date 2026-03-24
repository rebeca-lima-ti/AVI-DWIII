package com.autobots.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autobots.modelos.entidades.Endereco;
import com.autobots.repositorios.EnderecoRepositorio;

@Service
public class CriadorEndereco {
    @Autowired
    private EnderecoRepositorio enderecoRepositorio;

    @Transactional
    public void criarEndereco(Endereco endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço não pode ser nulo");
        }
        if (endereco.getEstado() == null || endereco.getEstado().isBlank()) {
            throw new IllegalArgumentException("Estado é obrigatório");
        }
        if (endereco.getCidade() == null || endereco.getCidade().isBlank()) {
            throw new IllegalArgumentException("Cidade é obrigatória");
        }
        if (endereco.getRua() == null || endereco.getRua().isBlank()) {
            throw new IllegalArgumentException("Rua é obrigatória");
        }
        if (endereco.getNumero() == null || endereco.getNumero().isBlank()) {
            throw new IllegalArgumentException("Número é obrigatório");
        }
        enderecoRepositorio.save(endereco);
    }
}
