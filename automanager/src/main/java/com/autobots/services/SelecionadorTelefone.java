package com.autobots.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.modelos.entidades.Telefone;
import com.autobots.repositorios.TelefoneRepositorio;

@Service
public class SelecionadorTelefone {

    @Autowired
    private TelefoneRepositorio telefoneRepositorio;

    public Telefone selecionar(long id) {
        Optional<Telefone> telefone = telefoneRepositorio.findById(id);
        return telefone.orElse(null);
    }
}
