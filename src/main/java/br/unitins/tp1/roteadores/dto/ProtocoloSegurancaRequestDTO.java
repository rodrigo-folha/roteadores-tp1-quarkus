package br.unitins.tp1.roteadores.dto;

import jakarta.validation.constraints.NotBlank;

public record ProtocoloSegurancaRequestDTO(
    @NotBlank(message = "O campo deve ser informado.")
    String nome
) {
    
}
