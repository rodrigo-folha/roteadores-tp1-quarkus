package br.unitins.tp1.roteadores.service.usuario;

import java.util.List;

import br.unitins.tp1.roteadores.dto.TelefoneRequestDTO;
import br.unitins.tp1.roteadores.dto.endereco.EnderecoRequestDTO;
import br.unitins.tp1.roteadores.dto.usuario.FuncionarioRequestDTO;
import br.unitins.tp1.roteadores.model.Telefone;
import br.unitins.tp1.roteadores.model.endereco.Endereco;
import br.unitins.tp1.roteadores.model.usuario.Funcionario;
import br.unitins.tp1.roteadores.model.usuario.Usuario;
import br.unitins.tp1.roteadores.repository.FuncionarioRepository;
import br.unitins.tp1.roteadores.repository.UsuarioRepository;
import br.unitins.tp1.roteadores.service.endereco.CidadeService;
import br.unitins.tp1.roteadores.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService {

    @Inject
    public FuncionarioRepository funcionarioRepository;

    @Inject
    public UsuarioRepository usuarioRepository;

    @Inject
    public CidadeService cidadeService;

    @Inject
    public HashService hashService;

    @Override
    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id);
    }

    @Override
    public List<Funcionario> findByNome(String nome) {
        return funcionarioRepository.findByNome(nome);
    }

    @Override
    public Funcionario findByUsuario(String email) {
        return funcionarioRepository.findByUsuario(email);
    }

    @Override
    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll().list();
    }

    @Override
    @Transactional
    public Funcionario create(FuncionarioRequestDTO dto) {
        if (usuarioRepository.findByEmail(dto.usuario().email()) != null)
            throw new ValidationException("email", "email já cadastrado.");

        if (usuarioRepository.findByCpf(dto.usuario().cpf()) != null)
            throw new ValidationException("cpf", "cpf já cadastrado.");

        Funcionario funcionario = new Funcionario();
        Usuario usuario = new Usuario();

        usuario.setNome(dto.usuario().nome());
        usuario.setCpf(dto.usuario().cpf());
        usuario.setDataNascimento(dto.usuario().dataNascimento());
        usuario.setEmail(dto.usuario().email());
        usuario.setSenha(hashService.getHashSenha(dto.usuario().senha()));
        usuario.setPerfil(dto.usuario().perfil());
        usuario.setTelefones(dto.usuario().telefones().stream().map(this::converterTelefone).toList());
        usuario.setEnderecos(dto.usuario().enderecos().stream().map(this::converterEndereco).toList());
        
        usuarioRepository.persist(usuario);
        funcionario.setUsuario(usuario);
        funcionario.setSalario(dto.salario());;
        funcionarioRepository.persist(funcionario);

        return funcionario;
    }

    @Override
    @Transactional
    public Funcionario update(Long id, FuncionarioRequestDTO dto) {
        if (funcionarioRepository.findById(id) == null)
            throw new ValidationException("id", "Id nao encontrado");

        if (usuarioRepository.findByEmail(dto.usuario().email()) != null)
            throw new ValidationException("email", "email já cadastrado.");

        if (usuarioRepository.findByCpf(dto.usuario().cpf()) != null)
            throw new ValidationException("cpf", "cpf já cadastrado.");

        Funcionario funcionario = funcionarioRepository.findById(id);
        Usuario usuario = funcionario.getUsuario();
        
        usuario.setNome(dto.usuario().nome());
        usuario.setCpf(dto.usuario().cpf());
        usuario.setDataNascimento(dto.usuario().dataNascimento());
        usuario.setEmail(dto.usuario().email());
        usuario.setSenha(hashService.getHashSenha(dto.usuario().senha()));
        usuario.setPerfil(dto.usuario().perfil());
        updateTelefones(usuario, dto.usuario().telefones());
        updateEnderecos(usuario, dto.usuario().enderecos());
        
        funcionario.setSalario(dto.salario());
        
        return funcionario;
    }

    @Override
    @Transactional
    public Funcionario updateNomeImagem(Long id, String nomeImagem) {
        Funcionario funcionario = funcionarioRepository.findById(id);

        funcionario.setNomeImagem(nomeImagem);
        return funcionario;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        funcionarioRepository.deleteById(id);
    }

    private Endereco converterEndereco(EnderecoRequestDTO enderecoDto) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoDto.logradouro());
        endereco.setBairro(enderecoDto.bairro());
        endereco.setNumero(enderecoDto.numero());
        endereco.setComplemento(enderecoDto.complemento());
        endereco.setCep(enderecoDto.cep());
        endereco.setCidade(cidadeService.findById(enderecoDto.idCidade()));
        return endereco;
    }
    
    private Telefone converterTelefone(TelefoneRequestDTO telefoneDto) {
        Telefone telefone = new Telefone();
        telefone.setCodigoArea(telefoneDto.codigoArea());
        telefone.setNumero(telefoneDto.numero());
        return telefone;
    }

    private void updateTelefones(Usuario usuario, List<TelefoneRequestDTO> novosTelefonesDTO) {
        List<Telefone> telefonesExistentes = usuario.getTelefones();
    
        telefonesExistentes.removeIf(telefone -> 
            novosTelefonesDTO.stream().noneMatch(dto -> 
                dto.codigoArea().equals(telefone.getCodigoArea()) && dto.numero().equals(telefone.getNumero()))
        );
    
        // Adicionar ou atualizar telefones
        for (TelefoneRequestDTO dto : novosTelefonesDTO) {
            Telefone telefoneExistente = telefonesExistentes.stream()
                .filter(t -> t.getCodigoArea().equals(dto.codigoArea()) && t.getNumero().equals(dto.numero()))
                .findFirst()
                .orElse(null);
    
            if (telefoneExistente == null) {
                Telefone novoTelefone = new Telefone();
                novoTelefone.setCodigoArea(dto.codigoArea());
                novoTelefone.setNumero(dto.numero());
                telefonesExistentes.add(novoTelefone);
            } else {
                telefoneExistente.setCodigoArea(dto.codigoArea());
                telefoneExistente.setNumero(dto.numero());
            }
        }
    }
    
    private void updateEnderecos(Usuario usuario, List<EnderecoRequestDTO> novosEnderecosDTO) {
        List<Endereco> enderecosExistentes = usuario.getEnderecos();
    
        enderecosExistentes.removeIf(endereco -> 
            novosEnderecosDTO.stream().noneMatch(dto -> 
                dto.logradouro().equals(endereco.getLogradouro()) && dto.numero().equals(endereco.getNumero()))
        );
    
        for (EnderecoRequestDTO dto : novosEnderecosDTO) {
            Endereco enderecoExistente = enderecosExistentes.stream()
                .filter(e -> e.getLogradouro().equals(dto.logradouro()) && e.getNumero().equals(dto.numero()))
                .findFirst()
                .orElse(null);
    
            if (enderecoExistente == null) {
                Endereco novoEndereco = new Endereco();
                novoEndereco.setLogradouro(dto.logradouro());
                novoEndereco.setBairro(dto.bairro());
                novoEndereco.setNumero(dto.numero());
                novoEndereco.setComplemento(dto.complemento());
                novoEndereco.setCep(dto.cep());
                novoEndereco.setCidade(cidadeService.findById(dto.idCidade()));
                enderecosExistentes.add(novoEndereco);
            } else {
                enderecoExistente.setLogradouro(dto.logradouro());
                enderecoExistente.setBairro(dto.bairro());
                enderecoExistente.setNumero(dto.numero());
                enderecoExistente.setComplemento(dto.complemento());
                enderecoExistente.setCep(dto.cep());
                enderecoExistente.setCidade(cidadeService.findById(dto.idCidade()));
            }
        }
    }
    
}
