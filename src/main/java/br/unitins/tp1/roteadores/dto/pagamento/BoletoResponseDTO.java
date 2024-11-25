package br.unitins.tp1.roteadores.dto.pagamento;

import java.time.LocalDate;

import br.unitins.tp1.roteadores.model.pagamento.Boleto;

public record BoletoResponseDTO(
    Long id,
    String codigoBarras,
    LocalDate validade,
    Double valor
){

    public static BoletoResponseDTO valueOf(Boleto boleto) {
        return new BoletoResponseDTO(
            boleto.getId(),
            boleto.getCodigoBarras(),
            boleto.getValidade(),
            boleto.getValor());
    }

}
