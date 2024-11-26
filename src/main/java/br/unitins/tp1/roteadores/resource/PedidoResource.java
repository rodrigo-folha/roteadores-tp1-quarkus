package br.unitins.tp1.roteadores.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.tp1.roteadores.dto.pagamento.CartaoRequestDTO;
import br.unitins.tp1.roteadores.dto.pedido.PedidoRequestDTO;
import br.unitins.tp1.roteadores.dto.pedido.PedidoResponseDTO;
import br.unitins.tp1.roteadores.dto.pedido.StatusPedidoRequestDTO;
import br.unitins.tp1.roteadores.service.pedido.PedidoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    public PedidoService pedidoService;

    @Inject
    public JsonWebToken jwt;

    @GET
    @RolesAllowed("User")
    public Response findByEmail() {

        // buscando o username do hash do jwt
        String username = jwt.getSubject();

        return Response.ok(pedidoService.findByEmail(username).
                    stream().
                    map(o -> PedidoResponseDTO.valueOf(o)).
                    toList()).build();
    }

    @POST
    @RolesAllowed("User")
    public Response create(@Valid PedidoRequestDTO dto) {

        // buscando o username do hash do jwt
        String username = jwt.getSubject();

        return Response.status(Status.CREATED).entity(
            PedidoResponseDTO.valueOf(pedidoService.create(dto, username))
        ).build();
    
    }

    @POST
    @RolesAllowed("User")
    @Path("/{idPedido}/pagamento/gerar/pix")
    public Response gerarPix(@PathParam("idPedido") Long id) {
        return Response.status(Status.CREATED).entity(pedidoService.gerarCodigoPix(id)).build();
    }

    @POST
    @RolesAllowed("User")
    @Path("/{idPedido}/pagamento/gerar/boleto")
    public Response gerarBoleto(@PathParam("idPedido") Long id) {
        return Response.status(Status.CREATED).entity(pedidoService.gerarBoleto(id)).build();
    }

    @PATCH
    @RolesAllowed("User")
    @Path("/{idPedido}/pagamento/pagar/pix/{idpix}")
    public Response registrarPagamentoPix(@PathParam("idPedido") Long idPedido, @PathParam("idpix") Long idPix) {
        pedidoService.registrarPagamentoPix(idPedido, idPix);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed("User")
    @Path("/{idPedido}/pagamento/pagar/boleto/{idboleto}")
    public Response registrarPagamentoBoleto(@PathParam("idPedido") Long idPedido, @PathParam("idboleto") Long idBoleto) {     
        pedidoService.registrarPagamentoBoleto(idPedido, idBoleto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed("User")
    @Path("/{idPedido}/pagamento/pagar/cartao/")
    public Response registrarPagamentoCartao(@PathParam("idPedido") Long idPedido, CartaoRequestDTO cartaoDTO) {     
        pedidoService.registrarPagamentoCartao(idPedido, cartaoDTO);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed("Adm")
    @Path("/statuspedido/{idPedido}")
    public Response updateStatusPedido(@PathParam("idPedido")Long idPedido, StatusPedidoRequestDTO statusPedido) {
        pedidoService.updateStatusPedido(idPedido, statusPedido);
        return Response.status(Status.NO_CONTENT).build();
    }

    
}
