package br.unitins.tp1.roteadores.service;

import java.util.List;

import br.unitins.tp1.roteadores.dto.BandaFrequenciaRequestDTO;
import br.unitins.tp1.roteadores.model.BandaFrequencia;
import br.unitins.tp1.roteadores.repository.BandaFrequenciaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BandaFrequenciaServiceImpl implements BandaFrequenciaService{

    @Inject
    public BandaFrequenciaRepository bandaFrequenciaRepository;

    @Override
    public BandaFrequencia findById(Long id) {
        return bandaFrequenciaRepository.findById(id);
    }

    @Override
    public List<BandaFrequencia> findByNome(String nome) {
        return bandaFrequenciaRepository.findByNome(nome);
    }

    @Override
    public List<BandaFrequencia> findAll() {
        return bandaFrequenciaRepository.findAll().list();
    }

    @Override
    @Transactional
    public BandaFrequencia create(BandaFrequenciaRequestDTO dto) {
        BandaFrequencia bandaFrequencia = new BandaFrequencia();
        bandaFrequencia.setNome(dto.nome());

        bandaFrequenciaRepository.persist(bandaFrequencia);
        return bandaFrequencia;
    }

    @Override
    @Transactional
    public BandaFrequencia update(Long id, BandaFrequenciaRequestDTO dto) {
        BandaFrequencia bandaFrequencia = bandaFrequenciaRepository.findById(id);
        bandaFrequencia.setNome(dto.nome());

        return bandaFrequencia;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bandaFrequenciaRepository.deleteById(id);
    }
    
}
