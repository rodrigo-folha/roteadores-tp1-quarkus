package br.unitins.tp1.roteadores.dto.pedido;

import java.util.List;

import br.unitins.tp1.roteadores.dto.endereco.EnderecoRequestDTO;
import jakarta.validation.Valid;

public record PedidoRequestDTO(
        @Valid
        List<ItemPedidoRequestDTO> listaItemPedido,
        @Valid
        EnderecoRequestDTO enderecoEntrega
) {
}
