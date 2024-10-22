package br.unitins.tp1.roteadores.resource;

import br.unitins.tp1.roteadores.dto.SistemaOperacionalRequestDTO;
import br.unitins.tp1.roteadores.dto.SistemaOperacionalResponseDTO;
import br.unitins.tp1.roteadores.service.SistemaOperacionalService;
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

@Path("/sistemasoperacionais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SistemaOperacionalResource {
    
    @Inject
    public SistemaOperacionalService sistemaOperacionalService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(sistemaOperacionalService.findById(id)).build();
    }
    
    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(sistemaOperacionalService.findByNome(nome)
            .stream()
            .map(SistemaOperacionalResponseDTO::valueOf)
            .toList()).build();
    }
    
    @GET
    public Response findAll() {
        return Response.ok(sistemaOperacionalService.findAll()
            .stream()
            .map(SistemaOperacionalResponseDTO::valueOf)
            .toList()).build();
    }

    @POST
    public Response create(@Valid SistemaOperacionalRequestDTO dto) {
        return Response.status(Status.CREATED)
            .entity(sistemaOperacionalService.create(dto))
            .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid SistemaOperacionalRequestDTO dto) {
        sistemaOperacionalService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        sistemaOperacionalService.delete(id);
        return Response.noContent().build();
    }
}
