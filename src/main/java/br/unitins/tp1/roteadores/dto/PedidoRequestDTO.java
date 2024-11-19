package br.unitins.tp1.roteadores.dto;

import java.util.List;

public record PedidoRequestDTO(

        Double valorTotal,
        List<ItemPedidoRequestDTO> listaItemPedido) {

}
