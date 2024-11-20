package br.unitins.tp1.roteadores.dto;

import java.time.LocalDate;
import java.util.List;

import br.unitins.tp1.roteadores.model.Perfil;
import br.unitins.tp1.roteadores.model.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String cpf,
    LocalDate dataNascimento,
    String email,
    String senha,
    Perfil perfil,
    List<TelefoneResponseDTO> telefones,
    List<EnderecoResponseDTO> enderecos
) {
    
    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getCpf(),
            usuario.getDataNascimento(),
            usuario.getEmail(),
            usuario.getSenha(),
            usuario.getPerfil(),
            usuario.getTelefones().stream().map(TelefoneResponseDTO::valueOf).toList(),
            usuario.getEnderecos().stream().map(EnderecoResponseDTO::valueOf).toList()
        );
    }
}
