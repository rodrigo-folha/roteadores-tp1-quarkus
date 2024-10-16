package br.unitins.tp1.roteadores.service;

import java.util.List;

import br.unitins.tp1.roteadores.dto.ProtocoloSegurancaRequestDTO;
import br.unitins.tp1.roteadores.model.ProtocoloSeguranca;
import br.unitins.tp1.roteadores.repository.ProtocoloSegurancaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProtocoloSegurancaServiceImpl implements ProtocoloSegurancaService{

    @Inject
    ProtocoloSegurancaRepository protocoloSegurancaRepository;

    @Override
    public ProtocoloSeguranca findById(Long id) {
        return protocoloSegurancaRepository.findById(id);
    }

    @Override
    public List<ProtocoloSeguranca> findByNome(String nome) {
        return protocoloSegurancaRepository.findByNome(nome);
    }

    @Override
    public List<ProtocoloSeguranca> findAll() {
        return protocoloSegurancaRepository.findAll().list();
    }

    @Override
    @Transactional
    public ProtocoloSeguranca create(ProtocoloSegurancaRequestDTO dto) {
        ProtocoloSeguranca protocoloSeguranca = new ProtocoloSeguranca();
        protocoloSeguranca.setNome(dto.nome());

        protocoloSegurancaRepository.persist(protocoloSeguranca);

        return protocoloSeguranca;
    }

    @Override
    @Transactional
    public ProtocoloSeguranca update(Long id, ProtocoloSegurancaRequestDTO dto) {
        ProtocoloSeguranca protocoloSeguranca = protocoloSegurancaRepository.findById(id);
        protocoloSeguranca.setNome(dto.nome());

        return protocoloSeguranca;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        protocoloSegurancaRepository.deleteById(id);
    }
    
}
