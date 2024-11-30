package br.unitins.tp1.roteadores.service.usuario;

import java.util.List;

import br.unitins.tp1.roteadores.dto.TelefoneRequestDTO;
import br.unitins.tp1.roteadores.dto.endereco.EnderecoRequestDTO;
import br.unitins.tp1.roteadores.dto.usuario.ClienteRequestDTO;
import br.unitins.tp1.roteadores.model.roteador.Roteador;
import br.unitins.tp1.roteadores.model.usuario.Cliente;

public interface ClienteService {

    Cliente findById(Long id);

    List<Cliente> findByNome(String nome);

    Cliente findByUsuario (String email);

    List<Cliente> findAll();

    Cliente create(ClienteRequestDTO dto);

    Cliente update(Long id, ClienteRequestDTO dto);

    Cliente updateNomeImagem(Long id, String nomeImagem);

    void delete(Long id);

    void updateEnderecoEspecifico(Long id, Long idEndereco, EnderecoRequestDTO dto);

    void updateEndereco(Long id, List<EnderecoRequestDTO> dto);

    void updateTelefoneEspecifico(Long id, Long idTelefone, TelefoneRequestDTO dto);

    void updateTelefone(Long id, List<TelefoneRequestDTO> dto);

    void adicionarProdutoListaDesejo(String email, Long idProduto);

    void removerProdutoListaDesejo(String email, Long idProduto);

    List<Roteador> getListaDesejos(String email);
    
}
