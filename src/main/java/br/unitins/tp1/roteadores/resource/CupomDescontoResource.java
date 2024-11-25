package br.unitins.tp1.roteadores.resource;

import br.unitins.tp1.roteadores.dto.pedido.CupomDescontoRequestDTO;
import br.unitins.tp1.roteadores.dto.pedido.CupomDescontoResponseDTO;
import br.unitins.tp1.roteadores.service.pedido.CupomDescontoService;
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

@Path("/cuponsdesconto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CupomDescontoResource {
    
    @Inject
    public CupomDescontoService cupomdescontoService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(cupomdescontoService.findById(id)).build();
    }

    @GET
    @Path("/search/{codigo}")
    public Response findByCodigo(@PathParam("codigo") String codigo) {
        return Response.ok(cupomdescontoService.findByCodigo(codigo)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(cupomdescontoService.findAll()
            .stream()
            .map(CupomDescontoResponseDTO::valueOf)
            .toList()).build();
    }

    @POST   
    public Response create(@Valid CupomDescontoRequestDTO dto) {
        return Response.status(Status.CREATED)
            .entity(cupomdescontoService.create(dto))
            .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid CupomDescontoRequestDTO dto) {
        cupomdescontoService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        cupomdescontoService.delete(id);
        return Response.noContent().build();
    }
}
