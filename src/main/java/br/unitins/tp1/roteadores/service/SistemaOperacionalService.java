package br.unitins.tp1.roteadores.service;

import java.util.List;

import br.unitins.tp1.roteadores.dto.SistemaOperacionalRequestDTO;
import br.unitins.tp1.roteadores.model.SistemaOperacional;

public interface SistemaOperacionalService {
    SistemaOperacional findById(Long id);
    
    List<SistemaOperacional> findByNome(String nome);

    List<SistemaOperacional> findAll();

    SistemaOperacional create(SistemaOperacionalRequestDTO dto);

    SistemaOperacional update(Long id, SistemaOperacionalRequestDTO dto);

    void delete(Long id);
}
