package br.unitins.tp1.roteadores.service.pedido;

import java.util.List;

import br.unitins.tp1.roteadores.dto.pagamento.BoletoResponseDTO;
import br.unitins.tp1.roteadores.dto.pagamento.CartaoRequestDTO;
import br.unitins.tp1.roteadores.dto.pagamento.PixResponseDTO;
import br.unitins.tp1.roteadores.dto.pedido.PedidoRequestDTO;
import br.unitins.tp1.roteadores.model.pedido.Pedido;
import br.unitins.tp1.roteadores.model.pedido.StatusPedido;

public interface PedidoService {

    Pedido findById(Long id);

    List<Pedido> findByEmail(String email);

    Pedido create(PedidoRequestDTO dto, String email);

    BoletoResponseDTO gerarBoleto(Long idPedido);
    PixResponseDTO gerarCodigoPix(Long idPedido);

    void registrarPagamentoPix(Long idPedido, Long idPix);
    void registrarPagamentoBoleto(Long idPedido, Long idBoleto);
    void registrarPagamentoCartao(Long idPedido, CartaoRequestDTO cartaoDTO);

    void updateStatusPedido(Long idPedido, StatusPedido statusPedido);
    
    // implementar os patches

    // pensasr no cancelar

}
