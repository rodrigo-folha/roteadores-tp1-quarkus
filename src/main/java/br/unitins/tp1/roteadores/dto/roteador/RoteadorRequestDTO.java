package br.unitins.tp1.roteadores.dto.roteador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @Positive(message = "Informe um campo valido para o id de SinalWireless")
    Long idSinalWireless,
    @Positive(message = "Informe um campo valido para o id de SistemaOperacional")
    Long idSistemaOperacional,
    @Positive(message = "Informe um campo valido para o id de Banda de Frequencia")
    Long idBandaFrequencia,
    @Positive(message = "Informe um campo valido para o id de Protocolo de Seguranca")
    Long idProtocoloSeguranca,
    @Positive(message = "Informe um campo valido para o id de Quantidade de Antenas")
    Long idQuantidadeAntena
    
){

}
