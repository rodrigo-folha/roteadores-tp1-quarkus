package br.unitins.tp1.roteadores.model.pagamento;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Pix extends Pagamento {

    private String chave;
    private LocalDate validade;

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

}
