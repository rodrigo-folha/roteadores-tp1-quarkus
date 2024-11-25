package br.unitins.tp1.roteadores.service.pedido;

import java.util.List;

import br.unitins.tp1.roteadores.dto.pedido.LoteRequestDTO;
import br.unitins.tp1.roteadores.model.pedido.Lote;
import br.unitins.tp1.roteadores.repository.LoteRepository;
import br.unitins.tp1.roteadores.service.roteador.RoteadorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LoteServiceImpl implements LoteService {

    @Inject
    public LoteRepository loteRepository;

    @Inject
    public RoteadorService roteadorService;

    @Override
    public Lote findById(Long id) {
        return loteRepository.findById(id);
    }

    @Override
    public Lote findByIdRoteador(Long idRoteador) {
        return loteRepository.findByIdRoteador(idRoteador);
    }

    @Override
    public List<Lote> findByIdRoteadorQtdeTotal(Long idRoteador) {
        return loteRepository.findByIdRoteadorQtdeTotal(idRoteador);
    }

    @Override
    public Lote findByCodigo(String codigo) {
        return loteRepository.findByCodigo(codigo);
    } 

    @Override
    public List<Lote> findAll() {
        return loteRepository.findAll().list();
    }

    @Override
    @Transactional
    public Lote create(LoteRequestDTO dto) {
        // buscando o estado a partir de um id do lote
        Lote lote = new Lote();
        lote.setRoteador(roteadorService.findById(dto.idRoteador()));
        lote.setCodigo(dto.codigo());
        lote.setData(dto.data());
        lote.setEstoque(dto.estoque());

        // salvando o lote
        loteRepository.persist(lote);

        return lote;
    }

    @Override
    @Transactional
    public Lote update(Long id, LoteRequestDTO dto) {
        Lote lote = loteRepository.findById(id);

        lote.setRoteador(roteadorService.findById(dto.idRoteador()));
        lote.setCodigo(dto.codigo());
        lote.setData(dto.data());
        lote.setEstoque(dto.estoque());
        
        return lote;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        loteRepository.deleteById(id);
    }
    
}
