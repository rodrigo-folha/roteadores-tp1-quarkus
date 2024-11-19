package br.unitins.tp1.roteadores.service;

import java.time.LocalDate;
import java.util.List;

import br.unitins.tp1.roteadores.dto.ClienteRequestDTO;
import br.unitins.tp1.roteadores.model.Cliente;
import br.unitins.tp1.roteadores.model.Usuario;
import br.unitins.tp1.roteadores.repository.ClienteRepository;
import br.unitins.tp1.roteadores.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    public ClienteRepository clienteRepository;

    @Inject
    public UsuarioRepository usuarioRepository;

    @Inject
    public HashService hashService;

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> findByNome(String nome) {
        return clienteRepository.findByNome(nome);
    }

    @Override
    public Cliente findByUsuario(String email) {
        return clienteRepository.findByUsuario(email);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll().list();
    }

    @Override
    @Transactional
    public Cliente create(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        Usuario usuario = new Usuario();

        usuario.setNome(dto.usuario().nome());
        usuario.setCpf(dto.usuario().cpf());
        usuario.setDataNascimento(dto.usuario().dataNascimento());
        usuario.setEmail(dto.usuario().email());
        usuario.setSenha(hashService.getHashSenha(dto.usuario().senha()));
        usuario.setPerfil(dto.usuario().perfil());
        
        usuarioRepository.persist(usuario);
        cliente.setUsuario(usuario);
        cliente.setDataCadastro(LocalDate.now());
        clienteRepository.persist(cliente);

        return cliente;
    }

    @Override
    @Transactional
    public Cliente update(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id);
        Usuario usuario = cliente.getUsuario();

        usuario.setNome(dto.usuario().nome());
        usuario.setCpf(dto.usuario().cpf());
        usuario.setDataNascimento(dto.usuario().dataNascimento());
        usuario.setEmail(dto.usuario().email());
        usuario.setSenha(hashService.getHashSenha(dto.usuario().senha()));
        usuario.setPerfil(dto.usuario().perfil());

        return cliente;
    }

    @Override
    @Transactional
    public Cliente updateNomeImagem(Long id, String nomeImagem) {
        Cliente cliente = clienteRepository.findById(id);

        cliente.setNomeImagem(nomeImagem);
        return cliente;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    
    
}
