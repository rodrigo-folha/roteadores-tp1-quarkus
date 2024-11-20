package br.unitins.tp1.roteadores.resource;

import java.io.IOException;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.tp1.roteadores.dto.FuncionarioRequestDTO;
import br.unitins.tp1.roteadores.dto.FuncionarioResponseDTO;
import br.unitins.tp1.roteadores.form.ImageForm;
import br.unitins.tp1.roteadores.service.FuncionarioFileServiceImpl;
import br.unitins.tp1.roteadores.service.FuncionarioService;
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

@Path("/funcionarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FuncionarioResource {

    @Inject
    public FuncionarioService funcionarioService;

    @Inject
    public FuncionarioFileServiceImpl funcionarioFileService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(FuncionarioResponseDTO.valueOf(funcionarioService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(funcionarioService.findByNome(nome)
                .stream()
                .map(FuncionarioResponseDTO::valueOf)
                .toList()).build();
    }

    @GET
    @Path("/search/{email}")
    public Response findByUsuario(@PathParam("email") String email) {
        return Response.ok(funcionarioService.findByNome(email)
                .stream()
                .map(FuncionarioResponseDTO::valueOf)
                .toList()).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(funcionarioService.findAll()
                .stream()
                .map(o -> FuncionarioResponseDTO.valueOf(o))
                .toList()).build();
    }

    @POST
    public Response create(@Valid FuncionarioRequestDTO dto) {
        return Response.status(Status.CREATED)
                .entity(FuncionarioResponseDTO.valueOf(funcionarioService.create(dto)))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, FuncionarioRequestDTO funcionario) {
        funcionarioService.update(id, funcionario);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        funcionarioService.delete(id);
        return Response.noContent().build();
    }

    @PATCH
    @Path("/{id}/upload/imagem")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@PathParam("id") Long id, @MultipartForm ImageForm form) {

        try {
            String nomeImagem = funcionarioFileService.save(form.getNomeImagem(), form.getImagem());

            funcionarioService.updateNomeImagem(id, nomeImagem);
        } catch (IOException e) {
            Response.status(500).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/download/imagem/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImagem(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(funcionarioFileService.find(nomeImagem));
        response.header("Content-Disposition", "attachment; filename=" + nomeImagem);
        return response.build();
    }
}
