package br.unitins.tp1.roteadores.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioBasicoRequestDTO(
    @NotBlank(message = "O campo nome deve ser informado.")
    String nome,
    @NotBlank(message = "O campo cpf deve ser informado.")
    String cpf,
    @NotBlank(message = "O campo email deve ser informado.")
    @Email
    String email,
    @NotBlank(message = "O campo senha deve ser informado.")
    String senha
) {
    
}
