package br.unitins.tp1.roteadores.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoRequestDTO(
    @NotBlank(message = "O campo logradouro deve ser informado") 
    String logradouro,
    @NotBlank(message = "O campo bairro deve ser informado") 
    String bairro,
    @NotBlank(message = "O campo numero deve ser informado") 
    String numero,
    String complemento,
    @NotBlank(message = "O campo CEP deve ser informado") 
    String cep,
    @NotNull(message = "O campo cidade deve ser informado") 
    Long idCidade
) {
    
}
