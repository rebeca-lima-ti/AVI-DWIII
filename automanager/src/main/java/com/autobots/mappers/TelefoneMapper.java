package com.autobots.mappers;

import org.springframework.stereotype.Component;

import com.autobots.modelos.dto.TelefoneDTO;
import com.autobots.modelos.entidades.Telefone;

@Component
public class TelefoneMapper {

    public Telefone toEntity(TelefoneDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Telefone telefone = new Telefone();
        telefone.setId(dto.getId());
        telefone.setDdd(dto.getDdd());
        telefone.setNumero(dto.getNumero());
        
        return telefone;
    }

    public TelefoneDTO toDTO(Telefone telefone) {
        if (telefone == null) {
            return null;
        }
        
        TelefoneDTO dto = new TelefoneDTO();
        dto.setId(telefone.getId());
        dto.setDdd(telefone.getDdd());
        dto.setNumero(telefone.getNumero());
        
        return dto;
    }
}
