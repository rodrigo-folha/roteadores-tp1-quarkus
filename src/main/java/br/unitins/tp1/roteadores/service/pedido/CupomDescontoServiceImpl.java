package br.unitins.tp1.roteadores.service.pedido;

import java.util.List;

import br.unitins.tp1.roteadores.dto.pedido.CupomDescontoRequestDTO;
import br.unitins.tp1.roteadores.model.pedido.CupomDesconto;
import br.unitins.tp1.roteadores.repository.CupomDescontoRepository;
import br.unitins.tp1.roteadores.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CupomDescontoServiceImpl implements CupomDescontoService {

    @Inject
    public CupomDescontoRepository cupomdescontoRepository;

    @Override
    public CupomDesconto findById(Long id) {
        return cupomdescontoRepository.findById(id);
    }

    @Override
    public CupomDesconto findByCodigo(String codigo) {   
        return cupomdescontoRepository.findByCodigo(codigo);
    } 

    @Override
    public List<CupomDesconto> findAll() {
        return cupomdescontoRepository.findAll().list();
    }

    @Override
    @Transactional
    public CupomDesconto create(CupomDescontoRequestDTO dto) {
        // buscando o estado a partir de um id do cupomdesconto
        CupomDesconto cupomdesconto = new CupomDesconto();
        cupomdesconto.setCodigo(dto.codigo());
        cupomdesconto.setPercentualDesconto(dto.percentualDesconto());
        cupomdesconto.setValidade(dto.validade());

        // salvando o cupomdesconto
        cupomdescontoRepository.persist(cupomdesconto);

        return cupomdesconto;
    }

    @Override
    @Transactional
    public CupomDesconto update(Long id, CupomDescontoRequestDTO dto) {
        CupomDesconto cupomdesconto = cupomdescontoRepository.findById(id);

        if (cupomdesconto == null)
            throw new ValidationException("id", "Id nao encontrado");
        
        cupomdesconto.setCodigo(dto.codigo());
        cupomdesconto.setPercentualDesconto(dto.percentualDesconto());
        cupomdesconto.setValidade(dto.validade());
        
        return cupomdesconto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (cupomdescontoRepository.findById(id) == null)
            throw new ValidationException("id", "id nao encontrado");
            
        cupomdescontoRepository.deleteById(id);
    }
    
}
