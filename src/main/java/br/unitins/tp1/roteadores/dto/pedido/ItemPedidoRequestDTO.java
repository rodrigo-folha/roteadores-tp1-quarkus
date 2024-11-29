package br.unitins.tp1.roteadores.dto.pedido;

import jakarta.validation.constraints.Positive;

public record ItemPedidoRequestDTO(
    @Positive(message = "O campo id do produto deve ser informado corretamente.")
    Long idProduto,
    @Positive(message = "Informe a quantidade")
    Integer quantidade
) {
    
}
