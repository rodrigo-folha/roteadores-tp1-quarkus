package br.unitins.tp1.roteadores.service;

import java.util.List;

import br.unitins.tp1.roteadores.dto.LoteRequestDTO;
import br.unitins.tp1.roteadores.model.Lote;

public interface LoteService {
    
    Lote findById(Long id);

    Lote findByCodigo(String codigos);

    Lote findByIdRoteador(Long idRoteador);

    List<Lote> findAll();

    Lote create(LoteRequestDTO dto);

    Lote update(Long id, LoteRequestDTO dto);

    void delete(Long id); 
}
