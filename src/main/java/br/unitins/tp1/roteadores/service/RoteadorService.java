package br.unitins.tp1.roteadores.service;

import java.util.List;

import br.unitins.tp1.roteadores.dto.RoteadorRequestDTO;
import br.unitins.tp1.roteadores.model.Roteador;

public interface RoteadorService {
    Roteador findById(Long id);

    List<Roteador> findByNome(String nome);

    List<Roteador> findBySinalWireless(Long id);

    List<Roteador> findBySistemaOperacional(Long id);

    List<Roteador> findByBandaFrequencia(Long id);

    List<Roteador> findByProtocoloSeguranca(Long id);

    List<Roteador> findByQuantidadeAntena(Long id);

    List<Roteador> findByPreco(Double min, Double max);

    List<Roteador> findAll();

    Roteador create(RoteadorRequestDTO dto);

    Roteador update(Long id, RoteadorRequestDTO dto);

    void delete(Long id);
}
