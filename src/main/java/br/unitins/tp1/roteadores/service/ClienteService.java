package br.unitins.tp1.roteadores.service;

import java.util.List;

import br.unitins.tp1.roteadores.dto.ClienteRequestDTO;
import br.unitins.tp1.roteadores.model.Cliente;

public interface ClienteService {

    Cliente findById(Long id);

    List<Cliente> findByNome(String nome);

    Cliente findByUsuario (String email);

    List<Cliente> findAll();

    Cliente create(ClienteRequestDTO dto);

    Cliente update(Long id, ClienteRequestDTO dto);

    Cliente updateNomeImagem(Long id, String nomeImagem);

    void delete(Long id);
    
}
