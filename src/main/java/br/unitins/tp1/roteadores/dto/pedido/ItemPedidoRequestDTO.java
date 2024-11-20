package br.unitins.tp1.roteadores.dto.pedido;

public record ItemPedidoRequestDTO(
    Long idProduto,
    Integer quantidade,
    Double preco
) {
    
}
