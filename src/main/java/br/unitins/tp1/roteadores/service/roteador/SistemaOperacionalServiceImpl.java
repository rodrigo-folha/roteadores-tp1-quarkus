package br.unitins.tp1.roteadores.service.roteador;

import java.util.List;

import br.unitins.tp1.roteadores.dto.roteador.SistemaOperacionalRequestDTO;
import br.unitins.tp1.roteadores.model.roteador.SistemaOperacional;
import br.unitins.tp1.roteadores.repository.SistemaOperacionalRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class SistemaOperacionalServiceImpl implements SistemaOperacionalService {

    @Inject
    public SistemaOperacionalRepository sistemaOperacionalRepository;

    @Override
    public SistemaOperacional findById(Long id) {
        return sistemaOperacionalRepository.findById(id);
    }

    @Override
    public List<SistemaOperacional> findByNome(String nome) {
        return sistemaOperacionalRepository.findByNome(nome);
    }

    @Override
    public List<SistemaOperacional> findAll() {
        return sistemaOperacionalRepository.findAll().list();
    }

    @Override
    @Transactional
    public SistemaOperacional create(SistemaOperacionalRequestDTO dto) {
        SistemaOperacional sistemaOperacional = new SistemaOperacional();
        sistemaOperacional.setNome(dto.nome());
    
        sistemaOperacionalRepository.persist(sistemaOperacional);

        return sistemaOperacional;
    }

    @Override
    @Transactional
    public SistemaOperacional update(Long id, SistemaOperacionalRequestDTO dto) {
        SistemaOperacional sistemaOperacional = sistemaOperacionalRepository.findById(id);

        sistemaOperacional.setNome(dto.nome());

        return sistemaOperacional;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        sistemaOperacionalRepository.deleteById(id);
    }
    
}
