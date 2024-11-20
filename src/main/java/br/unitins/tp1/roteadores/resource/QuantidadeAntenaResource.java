package br.unitins.tp1.roteadores.resource;

import br.unitins.tp1.roteadores.dto.roteador.QuantidadeAntenaRequestDTO;
import br.unitins.tp1.roteadores.dto.roteador.QuantidadeAntenaResponseDTO;
import br.unitins.tp1.roteadores.service.roteador.QuantidadeAntenaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("quantidadeantenas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuantidadeAntenaResource {

    @Inject
    public QuantidadeAntenaService quantidadeAntenaService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(quantidadeAntenaService.findById(id)).build();
    }

    @GET
    @Path("/search/{quantidade}")
    public Response findByQuantidade(@PathParam("quantidade") Integer quantidade) {
        return Response.ok(quantidadeAntenaService.findByQuantidade(quantidade)
            .stream()
            .map(QuantidadeAntenaResponseDTO::valueOf)
            .toList()).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(quantidadeAntenaService.findAll()
            .stream()
            .map(QuantidadeAntenaResponseDTO::valueOf)
            .toList()).build();
    }

    @POST
    public Response create(@Valid QuantidadeAntenaRequestDTO dto) {
        return Response.status(Status.CREATED)
            .entity(quantidadeAntenaService.create(dto))
            .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid QuantidadeAntenaRequestDTO dto) {
        quantidadeAntenaService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        quantidadeAntenaService.delete(id);
        return Response.noContent().build();
    }
}
