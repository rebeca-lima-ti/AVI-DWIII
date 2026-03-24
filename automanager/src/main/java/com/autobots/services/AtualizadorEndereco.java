package com.autobots.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autobots.modelos.entidades.Endereco;
import com.autobots.repositorios.EnderecoRepositorio;

@Service
public class AtualizadorEndereco {

    @Autowired
    private EnderecoRepositorio enderecoRepositorio;
    
    @Autowired
    private VerificadorStringNulo verificador;

    @Transactional
    public void atualizarEndereco(Endereco endereco, Endereco atualizacao) {
        if (endereco == null || atualizacao == null) {
            return;
        }
        
        if (!verificador.verificar(atualizacao.getRua())) {
            endereco.setRua(atualizacao.getRua());
        }
        if (!verificador.verificar(atualizacao.getNumero())) {
            endereco.setNumero(atualizacao.getNumero());
        }
        if (!verificador.verificar(atualizacao.getBairro())) {
            endereco.setBairro(atualizacao.getBairro());
        }
        if (!verificador.verificar(atualizacao.getCidade())) {
            endereco.setCidade(atualizacao.getCidade());
        }
        if (!verificador.verificar(atualizacao.getEstado())) {
            endereco.setEstado(atualizacao.getEstado());
        }
        if (!verificador.verificar(atualizacao.getCodigoPostal())) {
            endereco.setCodigoPostal(atualizacao.getCodigoPostal());
        }
        if (!verificador.verificar(atualizacao.getInformacoesAdicionais())) {
            endereco.setInformacoesAdicionais(atualizacao.getInformacoesAdicionais());
        }
        enderecoRepositorio.save(endereco);
    }
}
