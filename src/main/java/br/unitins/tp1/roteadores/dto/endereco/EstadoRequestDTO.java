package br.unitins.tp1.roteadores.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstadoRequestDTO(
    @NotBlank(message = "O campo deve ser informado.")
    @Size(max = 30, message = "O campo deve conter no máximo 30 caracteres.")
    String nome,
    @Size(min = 2, max = 2)
    String sigla
) {

}
