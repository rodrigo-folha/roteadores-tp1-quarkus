package br.unitins.tp1.roteadores.service;

import java.util.List;

import br.unitins.tp1.roteadores.dto.SinalWirelessRequestDTO;
import br.unitins.tp1.roteadores.model.SinalWireless;
import br.unitins.tp1.roteadores.repository.SinalWirelessRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class SinalWirelessServiceImpl implements SinalWirelessService {

    @Inject
    public SinalWirelessRepository sinalwirelessRepository;

    @Override
    public SinalWireless findById(Long id) {
        return sinalwirelessRepository.findById(id);
    }

    @Override
    public List<SinalWireless> findByNome(String nome) {
        return sinalwirelessRepository.findByNome(nome);
    }

    @Override
    public List<SinalWireless> findAll() {
        return sinalwirelessRepository.findAll().list();
    }

    @Override
    @Transactional
    public SinalWireless create(SinalWirelessRequestDTO dto) {
        SinalWireless sinalwireless = new SinalWireless();
        sinalwireless.setNome(dto.nome());

        sinalwirelessRepository.persist(sinalwireless);
        return sinalwireless;
    }

    @Override
    @Transactional
    public SinalWireless update(Long id, SinalWirelessRequestDTO dto) {
        SinalWireless sinalwireless = sinalwirelessRepository.findById(id);
        
        sinalwireless.setNome(dto.nome());
        
        return sinalwireless;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        sinalwirelessRepository.deleteById(id);
    }

 
    
}
