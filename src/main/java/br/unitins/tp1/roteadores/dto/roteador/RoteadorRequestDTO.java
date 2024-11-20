package br.unitins.tp1.roteadores.dto.roteador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RoteadorRequestDTO (
    @NotBlank(message = "O campo deve ser informado.")
    @Size(max = 255, message = "O campo nome deve conter no máximo 255 caracteres.")
    String nome,
    @NotNull(message = "O campo nao pode ser nulo.")
    @Size(max = 1000, message = "O campo descrição deve conter no máximo 1000 caracteres.")
    String descricao,
    @NotNull(message = "O campo nao pode ser nulo.")
    Double preco,
    @NotNull(message = "O idSinalWireless não pode ser nulo.")
    Long idSinalWireless,
    @NotNull(message = "O idSistemaOperacional não pode ser nulo.")
    Long idSistemaOperacional,
    @NotNull(message = "O idBandaFrequencia não pode ser nulo.")
    Long idBandaFrequencia,
    @NotNull(message = "O idProtocoloSeguranca não pode ser nulo.")
    Long idProtocoloSeguranca,
    @NotNull(message = "O idQuantidadeAntena não pode ser nulo.")
    Long idQuantidadeAntena
    
){

}
