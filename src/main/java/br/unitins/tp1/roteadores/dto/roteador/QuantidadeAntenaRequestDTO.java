package br.unitins.tp1.roteadores.dto;

import jakarta.validation.constraints.NotNull;

public record QuantidadeAntenaRequestDTO(
    @NotNull
    Integer quantidade
) {

}
