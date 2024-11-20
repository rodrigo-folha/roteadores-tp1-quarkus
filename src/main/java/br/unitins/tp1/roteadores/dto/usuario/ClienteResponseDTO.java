package br.unitins.tp1.roteadores.dto.usuario;

import java.time.LocalDate;

import br.unitins.tp1.roteadores.model.usuario.Cliente;

public record ClienteResponseDTO(
    Long id,
    LocalDate dataCadastro,
    String nomeImagem,
    UsuarioResponseDTO usuario
) {
    
    public static ClienteResponseDTO valueOf(Cliente cliente) {
        return new ClienteResponseDTO(
            cliente.getId(),
            cliente.getDataCadastro(),
            cliente.getNomeImagem(),
            UsuarioResponseDTO.valueOf(cliente.getUsuario()));
    }
}
