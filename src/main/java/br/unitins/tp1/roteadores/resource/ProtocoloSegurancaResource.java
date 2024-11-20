package br.unitins.tp1.roteadores.resource;

import br.unitins.tp1.roteadores.dto.roteador.ProtocoloSegurancaRequestDTO;
import br.unitins.tp1.roteadores.dto.roteador.ProtocoloSegurancaResponseDTO;
import br.unitins.tp1.roteadores.service.roteador.ProtocoloSegurancaService;
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

@Path("/protocolosseguranca")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProtocoloSegurancaResource {

    @Inject
    public ProtocoloSegurancaService protocoloSegurancaService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(protocoloSegurancaService.findById(id)).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(protocoloSegurancaService.findByNome(nome)
            .stream()
            .map(ProtocoloSegurancaResponseDTO::valueOf)
            .toList()).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(protocoloSegurancaService.findAll()
            .stream()
            .map(ProtocoloSegurancaResponseDTO::valueOf)
            .toList()).build();
    }

    @POST
    public Response create(@Valid ProtocoloSegurancaRequestDTO dto) {
        return Response.status(Status.CREATED)
            .entity(protocoloSegurancaService.create(dto))
            .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid ProtocoloSegurancaRequestDTO dto) {
        protocoloSegurancaService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        protocoloSegurancaService.delete(id);
        return Response.noContent().build();
    }
}
