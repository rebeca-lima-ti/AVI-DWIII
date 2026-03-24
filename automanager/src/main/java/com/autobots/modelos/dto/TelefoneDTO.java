package com.autobots.modelos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TelefoneDTO {
    private Long id;
    
    @Pattern(regexp = "^\\d{2}$", message = "DDD deve conter exatamente 2 dígitos")
    private String ddd;
    
    @NotBlank(message = "Número de telefone é obrigatório")
    @Pattern(regexp = "^\\d{8,9}$", message = "Número deve conter 8 ou 9 dígitos")
    private String numero;
}
