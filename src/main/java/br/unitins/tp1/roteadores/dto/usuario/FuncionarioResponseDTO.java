package br.unitins.tp1.roteadores.dto.usuario;

import br.unitins.tp1.roteadores.model.usuario.Funcionario;

public record FuncionarioResponseDTO(
    Long id,
    Double salario,
    String nomeImagem,
    UsuarioResponseDTO usuario
) {
    
    public static FuncionarioResponseDTO valueOf(Funcionario funcionario) {
        return new FuncionarioResponseDTO(
            funcionario.getId(),
            funcionario.getSalario(),
            funcionario.getNomeImagem(),
            UsuarioResponseDTO.valueOf(funcionario.getUsuario()));
    }
}
