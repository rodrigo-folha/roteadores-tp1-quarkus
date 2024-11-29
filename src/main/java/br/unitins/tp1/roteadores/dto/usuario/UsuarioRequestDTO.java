package br.unitins.tp1.roteadores.dto.usuario;

import java.time.LocalDate;
import java.util.List;

import br.unitins.tp1.roteadores.dto.TelefoneRequestDTO;
import br.unitins.tp1.roteadores.dto.endereco.EnderecoRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

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
    @Valid
    List<TelefoneRequestDTO> telefones,
    @Valid
    List<EnderecoRequestDTO> enderecos
) {
    
}
