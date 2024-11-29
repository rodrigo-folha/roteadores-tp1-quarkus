package br.unitins.tp1.roteadores.dto.pedido;

import java.util.List;

import br.unitins.tp1.roteadores.dto.endereco.EnderecoRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record PedidoRequestDTO(
        @NotNull(message = "O campo valor total deve ser informado.")
        Double valorTotal,
        @Valid
        List<ItemPedidoRequestDTO> listaItemPedido,
        @Valid
        EnderecoRequestDTO enderecoEntrega,
        String cupomDesconto,
        String tipoPagamento,
        Long idCartao
) {
}
