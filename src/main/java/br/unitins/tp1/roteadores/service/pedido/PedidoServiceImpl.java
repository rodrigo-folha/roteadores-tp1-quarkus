package br.unitins.tp1.roteadores.service.pedido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.unitins.tp1.roteadores.dto.endereco.EnderecoRequestDTO;
import br.unitins.tp1.roteadores.dto.pedido.ItemPedidoRequestDTO;
import br.unitins.tp1.roteadores.dto.pedido.PedidoRequestDTO;
import br.unitins.tp1.roteadores.model.pedido.CupomDesconto;
import br.unitins.tp1.roteadores.model.pedido.EnderecoEntrega;
import br.unitins.tp1.roteadores.model.pedido.ItemPedido;
import br.unitins.tp1.roteadores.model.pedido.Lote;
import br.unitins.tp1.roteadores.model.pedido.Pedido;
import br.unitins.tp1.roteadores.model.pedido.SituacaoPedido;
import br.unitins.tp1.roteadores.model.pedido.StatusPedido;
import br.unitins.tp1.roteadores.repository.PedidoRepository;
import br.unitins.tp1.roteadores.service.endereco.CidadeService;
import br.unitins.tp1.roteadores.service.usuario.ClienteService;
import br.unitins.tp1.roteadores.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    public PedidoRepository pedidoRepository;

    @Inject
    public ClienteService clienteService;

    @Inject
    public LoteService loteService;

    @Inject
    public CidadeService cidadeService;

    @Inject
    public CupomDescontoService cupomDescontoService;

    @Override
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public List<Pedido> findByEmail(String email) {
        return pedidoRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public Pedido create(PedidoRequestDTO dto, String email) {

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setCliente(clienteService.findByUsuario(email));
        pedido.setListaItemPedido(new ArrayList<ItemPedido>());

        Double desconto = 0.0;
        

        for (ItemPedidoRequestDTO itemDTO : dto.listaItemPedido()) {
            ItemPedido item = new ItemPedido();
            item.setPreco(0.0);
            Lote lote = loteService.findByIdRoteador(itemDTO.idProduto());
            if (lote == null)
                    throw new ValidationException("idProduto", "Por favor informe o id de algum produto valido");
            
            int qtdeTotalEstoque = loteService.findByIdRoteadorQtdeTotal(itemDTO.idProduto()).stream().reduce(0, (subtotal, b) -> subtotal + b.getEstoque(), Integer::sum);
            if (qtdeTotalEstoque < itemDTO.quantidade())
                throw new ValidationException("quantidade", "quantidade em estoque insuficiente");
            
            int qtdeRestante = itemDTO.quantidade();
            
            while (qtdeRestante > 0) {
                lote = loteService.findByIdRoteador(itemDTO.idProduto());
                
                int qtdeConsumida = Math.min(lote.getEstoque(), qtdeRestante);
                lote.setEstoque(lote.getEstoque() - qtdeConsumida);
                item.setPreco(item.getPreco() + (qtdeConsumida * lote.getRoteador().getPreco()));

                qtdeRestante -= qtdeConsumida;
            }

            item.setQuantidade(itemDTO.quantidade());
            item.setLote(lote);
            item.setPreco(lote.getRoteador().getPreco());
            item.setQuantidade(itemDTO.quantidade());

            pedido.getListaItemPedido().add(item);

        }

        String cupom = dto.listaItemPedido().getFirst().cupomDesconto();
        if (cupom != null) {
            CupomDesconto cupomDesconto = cupomDescontoService.findByCodigo(dto.listaItemPedido().getFirst().cupomDesconto());
            if (cupomDesconto == null)
                throw new ValidationException("cupom", "cupom invalido");

            desconto = cupomDesconto.getPercentualDesconto();
        }

        EnderecoRequestDTO enderecoDTO = dto.enderecoEntrega();
        pedido.setEnderecoEntrega(converterEndereco(enderecoDTO));

        // eh importante validar se o total enviado via dto eh o mesmo gerado pelos produtos
        pedido.setValorTotal(calcularTotal(pedido) * (1 - desconto));

        gerarStatusPedido(pedido);

        pedidoRepository.persist(pedido);

        return pedido;
    }

    private void gerarStatusPedido(Pedido pedido) {
        pedido.setStatusPedido(new ArrayList<>());
        StatusPedido statusPedido = new StatusPedido();
        statusPedido.setDataAtualizacao(LocalDateTime.now());
        statusPedido.setSituacaoPedido(SituacaoPedido.AGUARDANDO_PAGAMENTO);
        pedido.getStatusPedido().add(statusPedido);
    }

    private Double calcularTotal(Pedido pedido) {
        return pedido.getListaItemPedido()
                .stream()
                .mapToDouble(a -> a.getPreco() * a.getQuantidade())
                .sum();
    }

    private EnderecoEntrega converterEndereco(EnderecoRequestDTO enderecoDto) {
        EnderecoEntrega endereco = new EnderecoEntrega();
        endereco.setLogradouro(enderecoDto.logradouro());
        endereco.setBairro(enderecoDto.bairro());
        endereco.setNumero(enderecoDto.numero());
        endereco.setComplemento(enderecoDto.complemento());
        endereco.setCep(enderecoDto.cep());
        endereco.setCidade(cidadeService.findById(enderecoDto.idCidade()));
        return endereco;
    }



}

