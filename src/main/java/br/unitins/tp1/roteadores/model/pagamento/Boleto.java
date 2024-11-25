package br.unitins.tp1.roteadores.model.pagamento;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Boleto extends Pagamento {

    private String codigoBarras;
    private LocalDate validade;

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

}
