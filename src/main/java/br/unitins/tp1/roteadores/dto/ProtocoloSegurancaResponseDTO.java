package br.unitins.tp1.roteadores.dto;

import br.unitins.tp1.roteadores.model.ProtocoloSeguranca;

public record ProtocoloSegurancaResponseDTO(Long id, String nome) {
    public static ProtocoloSegurancaResponseDTO valueOf(ProtocoloSeguranca protocoloSeguranca) {
        return new ProtocoloSegurancaResponseDTO(protocoloSeguranca.getId(), protocoloSeguranca.getNome());
    }
    
}
