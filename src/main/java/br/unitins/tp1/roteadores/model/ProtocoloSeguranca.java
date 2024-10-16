package br.unitins.tp1.roteadores.model;

import jakarta.persistence.Entity;

@Entity
public class ProtocoloSeguranca extends DefaultEntity {
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
