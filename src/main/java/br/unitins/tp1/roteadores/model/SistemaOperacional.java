package br.unitins.tp1.roteadores.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class SistemaOperacional extends DefaultEntity{
    @Column(length = 20, nullable = false)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
