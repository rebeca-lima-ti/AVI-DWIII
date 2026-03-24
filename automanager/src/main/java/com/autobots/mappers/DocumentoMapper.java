package com.autobots.mappers;

import org.springframework.stereotype.Component;

import com.autobots.modelos.dto.DocumentoDTO;
import com.autobots.modelos.entidades.Documento;

@Component
public class DocumentoMapper {

    public Documento toEntity(DocumentoDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Documento documento = new Documento();
        documento.setId(dto.getId());
        documento.setTipo(dto.getTipo());
        documento.setNumero(dto.getNumero());
        
        return documento;
    }

    public DocumentoDTO toDTO(Documento documento) {
        if (documento == null) {
            return null;
        }
        
        DocumentoDTO dto = new DocumentoDTO();
        dto.setId(documento.getId());
        dto.setTipo(documento.getTipo());
        dto.setNumero(documento.getNumero());
        
        return dto;
    }
}
