package br.unitins.tp1.roteadores.dto.pedido;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record PedidoRequestDTO(
        @NotNull(message = "O campo valor total deve ser informado.")
        Double valorTotal,
        @Valid
        List<ItemPedidoRequestDTO> listaItemPedido,
        Long idEndereco,
        String cupomDesconto,
        String tipoPagamento,
        Long idCartao
) {
}
