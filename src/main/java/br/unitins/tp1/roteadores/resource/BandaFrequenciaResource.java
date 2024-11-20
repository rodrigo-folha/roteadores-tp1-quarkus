package br.unitins.tp1.roteadores.resource;

import br.unitins.tp1.roteadores.dto.roteador.BandaFrequenciaRequestDTO;
import br.unitins.tp1.roteadores.dto.roteador.BandaFrequenciaResponseDTO;
import br.unitins.tp1.roteadores.service.roteador.BandaFrequenciaService;
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

@Path("/bandafrequencias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BandaFrequenciaResource {
    
    @Inject
    public BandaFrequenciaService bandaFrequenciaService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(bandaFrequenciaService
            .findById(id))
            .build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(bandaFrequenciaService
            .findByNome(nome)
            .stream()
            .map(BandaFrequenciaResponseDTO::valueOf)
            .toList()).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(bandaFrequenciaService
            .findAll()
            .stream()
            .map(BandaFrequenciaResponseDTO::valueOf)
            .toList()).build();
    }

    @POST
    public Response create(@Valid BandaFrequenciaRequestDTO dto) {
        return Response.status(Status.CREATED)
            .entity(BandaFrequenciaResponseDTO.valueOf(bandaFrequenciaService.create(dto)))
            .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid BandaFrequenciaRequestDTO dto) {
        bandaFrequenciaService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        bandaFrequenciaService.delete(id);
        return Response.noContent().build();
    }
    
}
