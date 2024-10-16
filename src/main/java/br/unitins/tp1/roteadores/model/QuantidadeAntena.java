package br.unitins.tp1.roteadores.model;

import jakarta.persistence.Entity;

@Entity
public class QuantidadeAntena extends DefaultEntity {
    private Integer quantidade;

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}
