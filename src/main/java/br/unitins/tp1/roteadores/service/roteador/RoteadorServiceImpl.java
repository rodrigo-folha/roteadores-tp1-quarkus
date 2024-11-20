package br.unitins.tp1.roteadores.service;

import java.util.List;

import br.unitins.tp1.roteadores.dto.RoteadorRequestDTO;
import br.unitins.tp1.roteadores.model.Roteador;
import br.unitins.tp1.roteadores.repository.RoteadorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RoteadorServiceImpl implements RoteadorService {

    @Inject
    public RoteadorRepository roteadorRepository;

    @Inject
    public SinalWirelessService sinalWirelessService;

    @Inject
    public SistemaOperacionalService sistemaOperacionalService;

    @Inject
    public BandaFrequenciaService bandaFrequenciaService;

    @Inject
    public ProtocoloSegurancaService protocoloSegurancaService;

    @Inject
    public QuantidadeAntenaService quantidadeAntenaService;

    @Override
    public Roteador findById(Long id) {
        return roteadorRepository.findById(id);
    }

    @Override
    public List<Roteador> findByNome(String nome) {
        return roteadorRepository.findByNome(nome);
    }

    @Override
    public List<Roteador> findBySinalWireless(Long id) {
        return roteadorRepository.findBySinalWireless(id);
    }

    @Override
    public List<Roteador> findBySistemaOperacional(Long id) {
        return roteadorRepository.findBySistemaOperacional(id);
    }

    @Override
    public List<Roteador> findByBandaFrequencia(Long id) {
        return roteadorRepository.findByBandaFrequencia(id);
    }

    @Override
    public List<Roteador> findByProtocoloSeguranca(Long id) {
        return roteadorRepository.findByProtocoloSeguranca(id);
    }

    @Override
    public List<Roteador> findByQuantidadeAntena(Long id) {
        return roteadorRepository.findByQuantidadeAntena(id);
    }

    @Override
    public List<Roteador> findByPreco(Double min, Double max) {
        return roteadorRepository.findByPreco(min, max);
    }

    @Override
    public List<Roteador> findAll() {
        return roteadorRepository.findAll().list();
    }

    @Override
    @Transactional
    public Roteador create(RoteadorRequestDTO dto) {
        Roteador roteador = new Roteador();
        roteador.setNome(dto.nome());
        roteador.setDescricao(dto.descricao());
        roteador.setPreco(dto.preco());
        roteador.setSinalWireless(sinalWirelessService.findById(dto.idSinalWireless()));
        roteador.setSistemaOperacional(sistemaOperacionalService.findById(dto.idSistemaOperacional()));
        roteador.setBandaFrequencia(bandaFrequenciaService.findById(dto.idBandaFrequencia()));
        roteador.setProtocoloSeguranca(protocoloSegurancaService.findById(dto.idProtocoloSeguranca()));
        roteador.setQuantidadeAntena(quantidadeAntenaService.findById(dto.idQuantidadeAntena()));
        roteadorRepository.persist(roteador);

        return roteador;
    }

    @Override
    @Transactional
    public Roteador update(Long id, RoteadorRequestDTO dto) {
        Roteador roteador = roteadorRepository.findById(id);
        roteador.setNome(dto.nome());
        roteador.setDescricao(dto.descricao());
        roteador.setPreco(dto.preco());
        roteador.setSinalWireless(sinalWirelessService.findById(dto.idSinalWireless()));
        roteador.setSistemaOperacional(sistemaOperacionalService.findById(dto.idSistemaOperacional()));
        roteador.setBandaFrequencia(bandaFrequenciaService.findById(dto.idBandaFrequencia()));
        roteador.setProtocoloSeguranca(protocoloSegurancaService.findById(dto.idProtocoloSeguranca()));
        roteador.setQuantidadeAntena(quantidadeAntenaService.findById(dto.idQuantidadeAntena()));

        return roteador;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        roteadorRepository.deleteById(id);
    }

}
