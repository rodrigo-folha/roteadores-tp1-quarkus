package br.unitins.tp1.roteadores.dto;

import java.time.LocalDate;

import br.unitins.tp1.roteadores.model.Lote;

public record LoteResponseDTO(
    Long id,
    String codigo,
    LocalDate data,
    Integer estoque,
    RoteadorResponseDTO roteador
) {

    public static LoteResponseDTO valueOf(Lote lote) {
        return new LoteResponseDTO(
            lote.getId(),
            lote.getCodigo(),
            lote.getData(),
            lote.getEstoque(),
            RoteadorResponseDTO.valueOf(lote.getRoteador())
        );

    }
    
}
