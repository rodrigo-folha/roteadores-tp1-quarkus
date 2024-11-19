package br.unitins.tp1.roteadores.resource;

import java.io.IOException;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.tp1.roteadores.dto.ClienteRequestDTO;
import br.unitins.tp1.roteadores.dto.ClienteResponseDTO;
import br.unitins.tp1.roteadores.form.ClienteImageForm;
import br.unitins.tp1.roteadores.service.ClienteService;
import br.unitins.tp1.roteadores.service.FileService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    public ClienteService clienteService;

    @Inject
    public FileService clienteFileService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(ClienteResponseDTO.valueOf(clienteService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(clienteService.findByNome(nome)
                .stream()
                .map(ClienteResponseDTO::valueOf)
                .toList()).build();
    }

    @GET
    @Path("/search/{email}")
    public Response findByUsuario(@PathParam("email") String email) {
        return Response.ok(clienteService.findByNome(email)
                .stream()
                .map(ClienteResponseDTO::valueOf)
                .toList()).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(clienteService.findAll()
                .stream()
                .map(o -> ClienteResponseDTO.valueOf(o))
                .toList()).build();
    }

    @POST
    public Response create(@Valid ClienteRequestDTO dto) {
        return Response.status(Status.CREATED)
                .entity(ClienteResponseDTO.valueOf(clienteService.create(dto)))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ClienteRequestDTO cliente) {
        clienteService.update(id, cliente);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        clienteService.delete(id);
        return Response.noContent().build();
    }

    @PATCH
    @Path("/{id}/upload/imagem")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@PathParam("id") Long id, @MultipartForm ClienteImageForm form) {

        try {
            String nomeImagem = clienteFileService.save(form.getNomeImagem(), form.getImagem());

            clienteService.updateNomeImagem(id, nomeImagem);
        } catch (IOException e) {
            Response.status(500).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/download/imagem/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImagem(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(clienteFileService.find(nomeImagem));
        response.header("Content-Disposition", "attachment; filename=" + nomeImagem);
        return response.build();
    }
}
