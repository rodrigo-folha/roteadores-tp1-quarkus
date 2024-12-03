package br.unitins.tp1.roteadores.service.usuario;

import java.util.List;

import br.unitins.tp1.roteadores.dto.TelefoneRequestDTO;
import br.unitins.tp1.roteadores.dto.endereco.EnderecoRequestDTO;
import br.unitins.tp1.roteadores.dto.usuario.FuncionarioRequestDTO;
import br.unitins.tp1.roteadores.dto.usuario.FuncionarioUpdateRequestDTO;
import br.unitins.tp1.roteadores.model.usuario.Funcionario;

public interface FuncionarioService {

    Funcionario findById(Long id);

    List<Funcionario> findByNome(String nome);

    Funcionario findByUsuario (String email);

    List<Funcionario> findAll();

    Funcionario create(FuncionarioRequestDTO dto);

    Funcionario update(Long id, FuncionarioUpdateRequestDTO dto);

    Funcionario updateNomeImagem(Long id, String nomeImagem);

    void updateEnderecoEspecifico(Long id, Long idEndereco, EnderecoRequestDTO dto);

    void updateEndereco(Long id, List<EnderecoRequestDTO> dto);

    void updateTelefoneEspecifico(Long id, Long idTelefone, TelefoneRequestDTO dto);

    void updateTelefone(Long id, List<TelefoneRequestDTO> dto);

    void delete(Long id);

    Funcionario gerarFuncionarioFromCliente(String email);
    
}
