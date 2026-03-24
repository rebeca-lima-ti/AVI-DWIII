package com.autobots.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autobots.modelos.entidades.Telefone;
import com.autobots.repositorios.TelefoneRepositorio;

@Service
public class CriadorTelefone {
    @Autowired
    private TelefoneRepositorio telefoneRepositorio;

    @Transactional
    public void criarTelefone(Telefone telefone) {
        if (telefone == null) {
            throw new IllegalArgumentException("Telefone não pode ser nulo");
        }
        if (telefone.getNumero() == null || telefone.getNumero().isBlank()) {
            throw new IllegalArgumentException("Número de telefone é obrigatório");
        }
        telefoneRepositorio.save(telefone);
    }
}
