package br.unitins.tp1.roteadores.dto.pedido;

import java.time.LocalDate;

public record LoteRequestDTO(
    Long idRoteador,
    LocalDate data,
    String codigo,
    Integer estoque
) {
    
}
