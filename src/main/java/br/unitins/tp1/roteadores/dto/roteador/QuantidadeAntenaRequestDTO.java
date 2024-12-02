package br.unitins.tp1.roteadores.dto.roteador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record QuantidadeAntenaRequestDTO(
    @NotBlank(message = "O campo quantidade deve ser informado")
    @Positive
    Integer quantidade
) {

}
