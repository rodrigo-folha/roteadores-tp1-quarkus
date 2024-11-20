package br.unitins.tp1.roteadores.dto;

public record ItemPedidoRequestDTO(
    Long idProduto,
    Integer quantidade,
    Double preco
) {
    
}
