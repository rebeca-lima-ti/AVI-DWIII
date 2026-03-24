package com.autobots.mappers;

import org.springframework.stereotype.Component;

import com.autobots.modelos.dto.EnderecoDTO;
import com.autobots.modelos.entidades.Endereco;

@Component
public class EnderecoMapper {

    public Endereco toEntity(EnderecoDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Endereco endereco = new Endereco();
        endereco.setId(dto.getId());
        endereco.setEstado(dto.getEstado());
        endereco.setCidade(dto.getCidade());
        endereco.setBairro(dto.getBairro());
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setCodigoPostal(dto.getCodigoPostal());
        endereco.setInformacoesAdicionais(dto.getInformacoesAdicionais());
        
        return endereco;
    }

    public EnderecoDTO toDTO(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        
        EnderecoDTO dto = new EnderecoDTO();
        dto.setId(endereco.getId());
        dto.setEstado(endereco.getEstado());
        dto.setCidade(endereco.getCidade());
        dto.setBairro(endereco.getBairro());
        dto.setRua(endereco.getRua());
        dto.setNumero(endereco.getNumero());
        dto.setCodigoPostal(endereco.getCodigoPostal());
        dto.setInformacoesAdicionais(endereco.getInformacoesAdicionais());
        
        return dto;
    }
}
