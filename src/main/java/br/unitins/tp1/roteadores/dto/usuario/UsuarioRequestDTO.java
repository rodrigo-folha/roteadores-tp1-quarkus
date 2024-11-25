package br.unitins.tp1.roteadores.dto.usuario;

import java.time.LocalDate;
import java.util.List;

import br.unitins.tp1.roteadores.dto.TelefoneRequestDTO;
import br.unitins.tp1.roteadores.dto.endereco.EnderecoRequestDTO;
import br.unitins.tp1.roteadores.model.usuario.Perfil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDTO(
    @NotBlank(message = "O campo nome deve ser informado.")
    String nome,
    @NotBlank(message = "O campo cpf deve ser informado.")
    String cpf,
    LocalDate dataNascimento,
    @NotBlank(message = "O campo email deve ser informado.")
    @Email
    String email,
    @NotBlank(message = "O campo senha deve ser informado.")
    String senha,
    @NotNull(message = "O campo perfil deve ser informado.")
    Perfil perfil,
    @Valid
    List<TelefoneRequestDTO> telefones,
    @Valid
    List<EnderecoRequestDTO> enderecos
) {
    
}
