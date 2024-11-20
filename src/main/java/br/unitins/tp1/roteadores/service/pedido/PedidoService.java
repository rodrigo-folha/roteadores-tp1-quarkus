package br.unitins.tp1.roteadores.service.pedido;

import java.util.List;

import br.unitins.tp1.roteadores.dto.pedido.PedidoRequestDTO;
import br.unitins.tp1.roteadores.model.pedido.Pedido;

public interface PedidoService {

    Pedido findById(Long id);

    List<Pedido> findByEmail(String email);

    Pedido create(PedidoRequestDTO dto, String email);

    // implementar os patches

    // pensasr no cancelar

}
