package br.unitins.tp1.roteadores.dto;

import br.unitins.tp1.roteadores.model.Roteador;

public record RoteadorResponseDTO(
    Long id,
    String nome,
    String descricao,
    Double preco,
    SinalWirelessResponseDTO sinalWireless,
    SistemaOperacionalResponseDTO sistemaOperacional,
    BandaFrequenciaResponseDTO bandaFrequencia,
    ProtocoloSegurancaResponseDTO protocoloSeguranca,
    QuantidadeAntenaResponseDTO quantidadeAntena
) {
    public static RoteadorResponseDTO valueOf(Roteador roteador) {
        return new RoteadorResponseDTO(
            roteador.getId(),
            roteador.getNome(),
            roteador.getDescricao(),
            roteador.getPreco(),
            SinalWirelessResponseDTO.valueOf(roteador.getSinalWireless()),
            SistemaOperacionalResponseDTO.valueOf(roteador.getSistemaOperacional()),
            BandaFrequenciaResponseDTO.valueOf(roteador.getBandaFrequencia()),
            ProtocoloSegurancaResponseDTO.valueOf(roteador.getProtocoloSeguranca()),
            QuantidadeAntenaResponseDTO.valueOf(roteador.getQuantidadeAntena())
        );
    }
}
