package com.autobots.mappers;

import org.springframework.stereotype.Component;

import com.autobots.modelos.dto.ClienteDTO;
import com.autobots.modelos.entidades.Cliente;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNome(dto.getNome());
        cliente.setNomeSocial(dto.getNomeSocial());
        
        return cliente;
    }

    public ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setNomeSocial(cliente.getNomeSocial());
        
        return dto;
    }
}
