package br.unitins.tp1.roteadores.service;

import java.util.List;

import br.unitins.tp1.roteadores.dto.BandaFrequenciaRequestDTO;
import br.unitins.tp1.roteadores.model.BandaFrequencia;

public interface BandaFrequenciaService {
    
    BandaFrequencia findById(Long id);

    List<BandaFrequencia> findByNome(String nome);

    List<BandaFrequencia> findAll();

    BandaFrequencia create(BandaFrequenciaRequestDTO dto);

    BandaFrequencia update(Long id, BandaFrequenciaRequestDTO dto);

    void delete(Long id);
}
