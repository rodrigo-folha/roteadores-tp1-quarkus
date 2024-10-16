package br.unitins.tp1.roteadores.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Sexo {

    FEMININO(1, "Feminino"),
    MASCULINO(2, "Masculino");

    private final Integer id;
    private final String label;

    Sexo(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Sexo valueOf(Integer id) {
        if (id == null)
            return null;
        for (Sexo sexo : Sexo.values()) {
            if (sexo.getId().equals(id))
                return sexo;
        }
        throw new IllegalArgumentException("Id invalido");
    }

}
