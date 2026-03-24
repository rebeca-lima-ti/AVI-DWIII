package com.autobots.modelos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DocumentoDTO {
    private Long id;
    
    @NotBlank(message = "Tipo do documento é obrigatório")
    private String tipo;
    
    @NotBlank(message = "Número do documento é obrigatório")
    private String numero;
}
