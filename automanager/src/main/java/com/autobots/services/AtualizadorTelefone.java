package com.autobots.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autobots.modelos.entidades.Telefone;
import com.autobots.repositorios.TelefoneRepositorio;

@Service
public class AtualizadorTelefone {

    @Autowired
    private TelefoneRepositorio telefoneRepositorio;
    
    @Autowired
    private VerificadorStringNulo verificador;

    @Transactional
    public void atualizarTelefone(Telefone telefone, Telefone atualizacao) {
        if (telefone == null || atualizacao == null) {
            return;
        }
        
        if (!verificador.verificar(atualizacao.getDdd())) {
            telefone.setDdd(atualizacao.getDdd());
        }
        if (!verificador.verificar(atualizacao.getNumero())) {
            telefone.setNumero(atualizacao.getNumero());
        }
        telefoneRepositorio.save(telefone);
    }

    @Transactional
    public void atualizarTelefone(List<Telefone> telefones, List<Telefone> atualizacoes) {
        if (telefones == null || atualizacoes == null || atualizacoes.isEmpty()) {
            return;
        }
        
        for (Telefone atualizacao : atualizacoes) {
            if (atualizacao.getId() != null) {
                // Use stream em vez de loop O(n²)
                Optional<Telefone> telefoneOpt = telefones.stream()
                    .filter(t -> t != null && t.getId().equals(atualizacao.getId()))
                    .findFirst();
                
                if (telefoneOpt.isPresent()) {
                    Telefone telefone = telefoneOpt.get();
                    atualizarTelefone(telefone, atualizacao);
                }
            }
        }
    }
}
