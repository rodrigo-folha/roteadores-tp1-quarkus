package br.unitins.tp1.roteadores.dto;

import java.time.LocalDate;

import br.unitins.tp1.roteadores.model.Perfil;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
    @NotBlank(message = "O campo nome deve ser informado.")
    String nome,
    @NotBlank(message = "O campo cpf deve ser informado.")
    String cpf,
    LocalDate dataNascimento,
    @NotBlank(message = "O campo email deve ser informado.")
    String email,
    @NotBlank(message = "O campo email deve ser informado.")
    String senha,
    @NotBlank(message = "O campo email deve ser informado.")
    Perfil perfil
) {
    
}
