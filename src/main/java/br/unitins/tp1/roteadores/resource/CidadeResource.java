package br.unitins.tp1.roteadores.resource;

import br.unitins.tp1.roteadores.dto.endereco.CidadeRequestDTO;
import br.unitins.tp1.roteadores.dto.endereco.CidadeResponseDTO;
import br.unitins.tp1.roteadores.service.endereco.CidadeService;
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

@Path("/cidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CidadeResource {

    @Inject
    public CidadeService cidadeService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(CidadeResponseDTO.valueOf(cidadeService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(cidadeService.findByNome(nome)
                .stream()
                .map(o -> CidadeResponseDTO.valueOf(o))
                .toList()).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(cidadeService
                .findAll()
                .stream()
                .map(o -> CidadeResponseDTO.valueOf(o))
                .toList()).build();
    }

    @POST
    public Response create(@Valid CidadeRequestDTO dto) {
        return Response.status(Status.CREATED)
                .entity(CidadeResponseDTO.valueOf(cidadeService.create(dto)))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid CidadeRequestDTO dto) {
        cidadeService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        cidadeService.delete(id);
        return Response.noContent().build();
    }
}
