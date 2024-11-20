package br.unitins.tp1.roteadores.service;

import java.util.List;

import br.unitins.tp1.roteadores.dto.ProtocoloSegurancaRequestDTO;
import br.unitins.tp1.roteadores.model.ProtocoloSeguranca;

public interface ProtocoloSegurancaService {
    ProtocoloSeguranca findById(Long id);

    List<ProtocoloSeguranca> findByNome(String nome);

    List<ProtocoloSeguranca> findAll();

    ProtocoloSeguranca create(ProtocoloSegurancaRequestDTO dto);

    ProtocoloSeguranca update(Long id, ProtocoloSegurancaRequestDTO dto);

    void delete(Long id);
}
