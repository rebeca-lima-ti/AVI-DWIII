package com.autobots.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.modelos.entidades.Telefone;
import com.autobots.repositorios.TelefoneRepositorio;

@Service
public class ExcluidorTelefone {
    
    @Autowired
    private TelefoneRepositorio telefoneRepositorio;

    public void deletar(Telefone telefone) {
        if (telefone != null && telefone.getId() != null) {
            telefoneRepositorio.deleteById(telefone.getId());
        }
    }
}
