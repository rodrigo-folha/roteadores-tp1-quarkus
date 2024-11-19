package br.unitins.tp1.roteadores.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.unitins.tp1.roteadores.dto.ItemPedidoRequestDTO;
import br.unitins.tp1.roteadores.dto.PedidoRequestDTO;
import br.unitins.tp1.roteadores.model.ItemPedido;
import br.unitins.tp1.roteadores.model.Lote;
import br.unitins.tp1.roteadores.model.Pedido;
import br.unitins.tp1.roteadores.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    public PedidoRepository pedidoRepository;

    // @Inject
    // public UsuarioService usuarioService;

    @Inject
    public ClienteService clienteService;

    @Inject
    public LoteService loteService;

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
        // eh importante validar se o total enviado via dto eh o mesmo gerado pelos produtos
        pedido.setValorTotal(dto.valorTotal());

        pedido.setListaItemPedido(new ArrayList<ItemPedido>());

        for (ItemPedidoRequestDTO itemDTO : dto.listaItemPedido()) {
            ItemPedido item = new ItemPedido();
            Lote lote = loteService.findByIdRoteador(itemDTO.idProduto());
            item.setLote(lote);
            // eh importante validar o preco
            item.setPreco(itemDTO.preco());
            // eh importante validar se tem estoque
            item.setQuantidade(itemDTO.quantidade());

            pedido.getListaItemPedido().add(item);
        }

        pedidoRepository.persist(pedido);

        return pedido;
    }

}

