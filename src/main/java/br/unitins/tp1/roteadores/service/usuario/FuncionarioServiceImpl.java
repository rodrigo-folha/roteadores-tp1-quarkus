package br.unitins.tp1.roteadores.service.usuario;

import java.util.ArrayList;
import java.util.List;

import br.unitins.tp1.roteadores.dto.TelefoneRequestDTO;
import br.unitins.tp1.roteadores.dto.endereco.EnderecoRequestDTO;
import br.unitins.tp1.roteadores.dto.usuario.FuncionarioRequestDTO;
import br.unitins.tp1.roteadores.model.Telefone;
import br.unitins.tp1.roteadores.model.endereco.Endereco;
import br.unitins.tp1.roteadores.model.usuario.Cliente;
import br.unitins.tp1.roteadores.model.usuario.Funcionario;
import br.unitins.tp1.roteadores.model.usuario.Perfil;
import br.unitins.tp1.roteadores.model.usuario.Usuario;
import br.unitins.tp1.roteadores.repository.ClienteRepository;
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
    public ClienteRepository clienteRepository;

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
        if (usuario.getPerfis() == null)
            usuario.setPerfis(new ArrayList<>());
        usuario.getPerfis().add(Perfil.ADM);
        // usuario.setPerfil(Perfil.ADM);
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
    public Funcionario gerarFuncionarioFromCliente(String email) {
        Funcionario verificarFuncionario = funcionarioRepository.findByUsuario(email);

        if (verificarFuncionario != null)
            throw new ValidationException("email", "Ja existe um funcionario cadastrado para esse usuario");
        
        if (clienteRepository.findByUsuario(email) == null)
            throw new ValidationException("email", "Nao existe nenhum cliente cadastrado com esse email. Crie uma conta nova");

        Cliente cliente = clienteRepository.findByUsuario(email);
        Funcionario funcionario = new Funcionario();
        funcionario.setUsuario(cliente.getUsuario());
        Usuario usuario = funcionario.getUsuario();
        if (usuario.getPerfis() == null)
            usuario.setPerfis(new ArrayList<>());

        usuario.getPerfis().add(Perfil.ADM);
        // funcionario.getUsuario().setPerfil(Perfil.ADM);
        funcionario.setSalario(3000.0);

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
        if (usuario.getPerfis() == null)
            usuario.setPerfis(new ArrayList<>());
        usuario.getPerfis().add(Perfil.ADM);
        // usuario.setPerfil(Perfil.ADM);
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
    public void updateEnderecoEspecifico(Long id, Long idEndereco, EnderecoRequestDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(id);

        if (funcionario == null)
            throw new ValidationException("idFuncionario", "Funcionario nao encontrado");

        Endereco endereco = funcionario.getUsuario().getEnderecos().stream().filter(a -> a.getId().equals((idEndereco)))
                .findFirst()
                .orElseThrow(() -> new ValidationException("idEndereco", "Endereco nao encontrado"));

        endereco.setLogradouro(dto.logradouro());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());
        endereco.setBairro(dto.bairro());
        endereco.setCep(dto.cep());
        endereco.setCidade(cidadeService.findById(dto.idCidade()));

        funcionario.getUsuario().setEnderecos(funcionario.getUsuario().getEnderecos());
    }

    @Override
    @Transactional
    public void updateEndereco(Long id, List<EnderecoRequestDTO> dto) {
        Funcionario funcionario = funcionarioRepository.findById(id);

        if (funcionario == null)
            throw new ValidationException("idfuncionario", "Funcionario nao encontrado");

        updateEnderecos(funcionario.getUsuario(), dto);
    }

    @Override
    @Transactional
    public void updateTelefoneEspecifico(Long id, Long idTelefone, TelefoneRequestDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(id);

        if (funcionario == null)
            throw new ValidationException("idfuncionario", "Funcionario nao encontrado");

        Telefone telefone = funcionario.getUsuario().getTelefones().stream().filter(a -> a.getId().equals((idTelefone)))
                .findFirst()
                .orElseThrow(() -> new ValidationException("idEndereco", "Endereco nao encontrado"));
        
        telefone.setCodigoArea(dto.codigoArea());
        telefone.setNumero(dto.numero());

        funcionario.getUsuario().setTelefones(funcionario.getUsuario().getTelefones());
    }

    @Override
    @Transactional
    public void updateTelefone(Long id, List<TelefoneRequestDTO> dto) {
        Funcionario funcionario = funcionarioRepository.findById(id);

        if (funcionario == null)
            throw new ValidationException("idfuncionario", "funcionario nao encontrado");

        updateTelefones(funcionario.getUsuario(), dto);
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
