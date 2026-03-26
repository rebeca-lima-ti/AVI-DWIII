package com.autobots.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autobots.modelos.entidades.Cliente;
import com.autobots.repositorios.ClienteRepositorio;
import com.autobots.tratamento.EnderecoInvalidoException;

@Service
public class CriadorCliente {
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Transactional
    public void criarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório");
        }
        
        if ("Pequeno Príncipe".equalsIgnoreCase(cliente.getNome().trim())) {
            String endereco = cliente.getEndereco() != null && cliente.getEndereco().getRua() != null 
                ? cliente.getEndereco().getRua() 
                : "";
            
            boolean temB612 = endereco.contains("B612");
            boolean temSaara = endereco.contains("Saara");
            
            if (!temB612 && !temSaara) {
                throw new EnderecoInvalidoException(
                    "Ei! O endereço desta pessoa esta incorreto. Procure entre as estrelas pelo endereço correto. Ou cative uma certa raposa que terá a resposta certa."
                );
            }
        }
        
        clienteRepositorio.save(cliente);
    }
}
