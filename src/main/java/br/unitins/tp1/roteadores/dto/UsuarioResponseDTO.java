package br.unitins.tp1.roteadores.dto;

import java.time.LocalDate;

import br.unitins.tp1.roteadores.model.Perfil;
import br.unitins.tp1.roteadores.model.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String cpf,
    LocalDate dataNascimento,
    String email,
    String senha,
    Perfil perfil
) {
    
    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getCpf(),
            usuario.getDataNascimento(),
            usuario.getEmail(),
            usuario.getSenha(),
            usuario.getPerfil()
        );
    }
}
