package br.unitins.tp1.roteadores.service;

import java.util.List;

import br.unitins.tp1.roteadores.dto.FuncionarioRequestDTO;
import br.unitins.tp1.roteadores.model.Funcionario;

public interface FuncionarioService {

    Funcionario findById(Long id);

    List<Funcionario> findByNome(String nome);

    Funcionario findByUsuario (String email);

    List<Funcionario> findAll();

    Funcionario create(FuncionarioRequestDTO dto);

    Funcionario update(Long id, FuncionarioRequestDTO dto);

    Funcionario updateNomeImagem(Long id, String nomeImagem);

    void delete(Long id);
    
}
