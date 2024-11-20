package br.unitins.tp1.roteadores.dto;

import java.time.LocalDate;

public record LoteRequestDTO(
    Long idRoteador,
    LocalDate data,
    String codigo,
    Integer estoque
) {
    
}
