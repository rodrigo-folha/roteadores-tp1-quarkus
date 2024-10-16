package br.unitins.tp1.roteadores.resource;

import br.unitins.tp1.roteadores.dto.RoteadorRequestDTO;
import br.unitins.tp1.roteadores.dto.RoteadorResponseDTO;
import br.unitins.tp1.roteadores.service.RoteadorService;
import jakarta.inject.Inject;
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

@Path("/roteadores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoteadorResource {

    @Inject
    RoteadorService roteadorService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(RoteadorResponseDTO.valueOf(roteadorService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(roteadorService.findByNome(nome)
            .stream()
            .map(o -> RoteadorResponseDTO.valueOf(o))
            .toList()).build();
    }

    @GET
    @Path("/search/sinalwireless/{id}")
    public Response findBySinalWireless(@PathParam("id") Long id) {
        return Response.ok(roteadorService.findBySinalWireless(id)
            .stream()
            .map(RoteadorResponseDTO::valueOf)
            .toList()).build();
    }

    @GET
    @Path("/search/sistemasoperacionais/{id}")
    public Response findBySistemaOperacional(@PathParam("id") Long id) {
        return Response.ok(roteadorService.findBySistemaOperacional(id)
            .stream()
            .map(RoteadorResponseDTO::valueOf)
            .toList()).build();
    }
    
    @GET
    @Path("/search/quantidadeantenas/{id}")
    public Response findByQuantidadeAntena(@PathParam("id") Long id) {
        return Response.ok(roteadorService.findByQuantidadeAntena(id)
            .stream()
            .map(RoteadorResponseDTO::valueOf)
            .toList()).build();
    }

    @GET
    @Path("/search/bandafrequencias/{id}")
    public Response findByBandaFrequencia(@PathParam("id") Long id) {
        return Response.ok(roteadorService.findByBandaFrequencia(id)
            .stream()
            .map(RoteadorResponseDTO::valueOf)
            .toList()).build();
    }

    @GET
    @Path("/search/protocolosseguranca/{id}")
    public Response findByProtocoloSeguranca(@PathParam("id") Long id) {
        return Response.ok(roteadorService.findByProtocoloSeguranca(id)
            .stream()
            .map(RoteadorResponseDTO::valueOf)
            .toList()).build();
    }

    @GET
    @Path("/search/preco/{min}/{max}")
    public Response findByPreco(@PathParam("min") Double min, @PathParam("max") Double max) {
        return Response.ok(roteadorService.findByPreco(min, max)
            .stream()
            .map(RoteadorResponseDTO::valueOf)
            .toList()).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(roteadorService.findAll()
            .stream()
            .map(RoteadorResponseDTO::valueOf)
            .toList()).build();
    }

    @POST
    public Response create(RoteadorRequestDTO dto) {
        return Response.status(Status.CREATED)
            .entity(RoteadorResponseDTO.valueOf(roteadorService.create(dto)))
            .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, RoteadorRequestDTO roteador) {
        roteadorService.update(id, roteador);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        roteadorService.delete(id);
        return Response.noContent().build();
    }
    
}
