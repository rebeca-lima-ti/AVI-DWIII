package com.autobots.modelos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteDTO {
    private Long id;
    
    @NotBlank(message = "Nome do cliente é obrigatório")
    private String nome;
    
    private String nomeSocial;
}
