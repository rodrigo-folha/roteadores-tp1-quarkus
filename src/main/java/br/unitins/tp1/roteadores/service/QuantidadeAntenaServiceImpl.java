package br.unitins.tp1.roteadores.service;

import java.util.List;

import br.unitins.tp1.roteadores.dto.QuantidadeAntenaRequestDTO;
import br.unitins.tp1.roteadores.model.QuantidadeAntena;
import br.unitins.tp1.roteadores.repository.QuantidadeAntenaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class QuantidadeAntenaServiceImpl implements QuantidadeAntenaService {

    @Inject
    QuantidadeAntenaRepository quantidadeAntenaRepository;

    @Override
    public QuantidadeAntena findById(Long id) {
        return quantidadeAntenaRepository.findById(id);
    }

    @Override
    public List<QuantidadeAntena> findByQuantidade(Integer quantidade) {
        return quantidadeAntenaRepository.findByQuantidade(quantidade);
    }

    @Override
    public List<QuantidadeAntena> findAll() {
        return quantidadeAntenaRepository.findAll().list();
    }

    @Override
    @Transactional
    public QuantidadeAntena create(QuantidadeAntenaRequestDTO dto) {
        QuantidadeAntena quantidadeAntena = new QuantidadeAntena();
        quantidadeAntena.setQuantidade(dto.quantidade());

        quantidadeAntenaRepository.persist(quantidadeAntena);

        return quantidadeAntena;
    }

    @Override
    @Transactional
    public QuantidadeAntena update(Long id, QuantidadeAntenaRequestDTO dto) {
        QuantidadeAntena quantidadeAntena = quantidadeAntenaRepository.findById(id);
        quantidadeAntena.setQuantidade(dto.quantidade());

        return quantidadeAntena;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        quantidadeAntenaRepository.deleteById(id);
    }

}
