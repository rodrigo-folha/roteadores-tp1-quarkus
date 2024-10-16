package br.unitins.tp1.roteadores.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CidadeRequestDTO(
    @NotBlank(message = "O campo nome deve ser informado.")
    @Size(max = 60, message = "O campo nome deve conter no máximo 60 caracteres.")
    String nome,
    @NotNull(message = "O idEstado não pode ser nulo.")
    Long idEstado
) {


}
