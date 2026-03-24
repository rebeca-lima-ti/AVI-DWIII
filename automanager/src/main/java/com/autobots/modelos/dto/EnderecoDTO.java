package com.autobots.modelos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EnderecoDTO {
    private Long id;
    
    @NotBlank(message = "Estado é obrigatório")
    private String estado;
    
    @NotBlank(message = "Cidade é obrigatória")
    private String cidade;
    
    private String bairro;
    
    @NotBlank(message = "Rua é obrigatória")
    private String rua;
    
    @NotBlank(message = "Número é obrigatório")
    private String numero;
    
    @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "Formato de CEP inválido (XXXXX-XXX ou XXXXXXX)")
    private String codigoPostal;
    
    private String informacoesAdicionais;
}
