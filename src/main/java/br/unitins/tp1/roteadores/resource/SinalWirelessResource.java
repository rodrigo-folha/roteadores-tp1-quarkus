package br.unitins.tp1.roteadores.resource;

import br.unitins.tp1.roteadores.dto.roteador.SinalWirelessRequestDTO;
import br.unitins.tp1.roteadores.dto.roteador.SinalWirelessResponseDTO;
import br.unitins.tp1.roteadores.service.roteador.SinalWirelessService;
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

@Path("/sinalwireless")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SinalWirelessResource {
    
    @Inject
    public SinalWirelessService sinalWirelessService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(sinalWirelessService.findById(id)).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(sinalWirelessService.findByNome(nome)
            .stream()
            .map(SinalWirelessResponseDTO::valueOf).toList())
            .build();
    }

    @GET
    public Response findAll() {
        return Response.ok(sinalWirelessService.findAll()
            .stream()
            .map(SinalWirelessResponseDTO::valueOf))
            .build();
    }

    @POST
    public Response create(@Valid SinalWirelessRequestDTO dto) {
        return Response.status(Status.CREATED)
            .entity(sinalWirelessService.create(dto))
            .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid SinalWirelessRequestDTO dto) {
        sinalWirelessService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        sinalWirelessService.delete(id);
        return Response.noContent().build();
    }

}
