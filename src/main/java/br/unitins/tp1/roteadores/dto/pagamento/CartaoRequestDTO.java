package br.unitins.tp1.roteadores.dto.pagamento;

import java.time.LocalDate;

import br.unitins.tp1.roteadores.model.pagamento.ModalidadeCartao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CartaoRequestDTO(
    @NotBlank(message = "O campo nome do titular deve ser informado.")
    String titular,
    @NotBlank(message = "O campo cpf deve ser informado.")
    String cpfCartao,
    @NotBlank(message = "O campo numero deve ser informado.")
    String numero,
    @NotBlank(message = "O campo data de validade deve ser informado.")
    LocalDate dataValidade,
    @NotBlank(message = "O campo cvc deve ser informado.")
    String cvc,
    @NotNull(message = "O campo modalidade deve ser informado.")
    ModalidadeCartao modalidade
) {
    
}
