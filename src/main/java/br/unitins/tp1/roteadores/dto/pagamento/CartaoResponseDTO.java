package br.unitins.tp1.roteadores.dto.pagamento;

import br.unitins.tp1.roteadores.model.pagamento.Cartao;

public record CartaoResponseDTO(
    Long id,
    String titular,
    String cpfCartao,
    String numero
) {
    
    public static CartaoResponseDTO valueOf(Cartao cartao) {
        return new CartaoResponseDTO(
            cartao.getId(),
            cartao.getTitular(),
            cartao.getCpfCartao(),
            cartao.getNumero());
    }
}
