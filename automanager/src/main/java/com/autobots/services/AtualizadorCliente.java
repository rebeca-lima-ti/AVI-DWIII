package com.autobots.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autobots.modelos.entidades.Cliente;
import com.autobots.repositorios.ClienteRepositorio;

@Service
public class AtualizadorCliente {

    @Autowired
    private ClienteRepositorio clienteRepositorio;
    
    @Autowired
    private VerificadorStringNulo verificador;
    
    @Autowired
    private AtualizadorEndereco atualizadorEndereco;
    
    @Autowired
    private AtualizadorDocumento atualizadorDocumento;
    
    @Autowired
    private AtualizadorTelefone atualizadorTelefone;

    public void atualizarDados(Cliente cliente, Cliente atualizacao) {
        if (atualizacao == null) {
            return;
        }
        
        if (!verificador.verificar(atualizacao.getNome())) {
            cliente.setNome(atualizacao.getNome());
        }
        if (!verificador.verificar(atualizacao.getNomeSocial())) {
            cliente.setNomeSocial(atualizacao.getNomeSocial());
        }
        if (atualizacao.getDataCadastro() != null) {
            cliente.setDataCadastro(atualizacao.getDataCadastro());
        }
        if (atualizacao.getDataNascimento() != null) {
            cliente.setDataNascimento(atualizacao.getDataNascimento());
        }
    }

    @Transactional
    public void atualizar(Cliente cliente, Cliente atualizacao) {
        if (cliente == null || atualizacao == null) {
            throw new IllegalArgumentException("Cliente e atualizacao não podem ser nulos");
        }
        
        atualizarDados(cliente, atualizacao);
        
        if (atualizacao.getEndereco() != null && cliente.getEndereco() != null) {
            atualizadorEndereco.atualizarEndereco(cliente.getEndereco(), atualizacao.getEndereco());
        }
        
        if (atualizacao.getDocumentos() != null && !atualizacao.getDocumentos().isEmpty()) {
            atualizadorDocumento.atualizarDocumento(cliente.getDocumentos(), atualizacao.getDocumentos());
        }
        
        if (atualizacao.getTelefones() != null && !atualizacao.getTelefones().isEmpty()) {
            atualizadorTelefone.atualizarTelefone(cliente.getTelefones(), atualizacao.getTelefones());
        }
        
        clienteRepositorio.save(cliente);
    }
}
