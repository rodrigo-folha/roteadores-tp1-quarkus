package br.unitins.tp1.roteadores.dto.usuario;

import java.time.LocalDate;
import java.util.List;

import br.unitins.tp1.roteadores.dto.TelefoneRequestDTO;
import br.unitins.tp1.roteadores.dto.endereco.EnderecoRequestDTO;
import br.unitins.tp1.roteadores.model.usuario.Perfil;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
    @NotBlank(message = "O campo nome deve ser informado.")
    String nome,
    @NotBlank(message = "O campo cpf deve ser informado.")
    String cpf,
    LocalDate dataNascimento,
    @NotBlank(message = "O campo email deve ser informado.")
    String email,
    @NotBlank(message = "O campo senha deve ser informado.")
    String senha,
    @NotBlank(message = "O campo perfil deve ser informado.")
    Perfil perfil,
    List<TelefoneRequestDTO> telefones,
    List<EnderecoRequestDTO> enderecos
) {
    
}
