package br.unitins.tp1.roteadores.dto.endereco;

import br.unitins.tp1.roteadores.model.endereco.Endereco;

public record EnderecoResponseDTO(
    Long id,
    String logradouro,
    String bairro,
    String numero,
    String complemento,
    String cep,
    CidadeResponseDTO cidade
) {

    public static EnderecoResponseDTO valueOf(Endereco endereco) {
        return new EnderecoResponseDTO(endereco.getId(), 
            endereco.getLogradouro(), 
            endereco.getBairro(),
            endereco.getNumero(),
            endereco.getComplemento(),
            endereco.getCep(),
            CidadeResponseDTO.valueOf(endereco.getCidade()));
    }
    
}
