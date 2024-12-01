package br.unitins.tp1.roteadores.resource;

import java.io.IOException;
import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.tp1.roteadores.dto.TelefoneRequestDTO;
import br.unitins.tp1.roteadores.dto.endereco.EnderecoRequestDTO;
import br.unitins.tp1.roteadores.dto.usuario.ClienteRequestDTO;
import br.unitins.tp1.roteadores.dto.usuario.ClienteResponseDTO;
import br.unitins.tp1.roteadores.form.ImageForm;
import br.unitins.tp1.roteadores.service.usuario.ClienteFileServiceImpl;
import br.unitins.tp1.roteadores.service.usuario.ClienteService;
import br.unitins.tp1.roteadores.service.usuario.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
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

    private static final Logger LOG = Logger.getLogger(ClienteResource.class);

    @Inject
    public ClienteService clienteService;

    @Inject 
    public UsuarioService usuarioService;

    @Inject
    public JsonWebToken jsonWebToken;

    @Inject
    public ClienteFileServiceImpl clienteFileService;

    @GET
    // @RolesAllowed({"Adm"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Execucao do metodo findById. Id: " + id);
        return Response.ok(ClienteResponseDTO.valueOf(clienteService.findById(id))).build();
    }

    @GET
    // @RolesAllowed({"Adm"})
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.info("Execucao do metodo findByNome. Nome: " + nome);
        return Response.ok(clienteService.findByNome(nome)
                .stream()
                .map(ClienteResponseDTO::valueOf)
                .toList()).build();
    }

    @GET
    // @RolesAllowed({"Adm"})
    @Path("/search/email/{email}")
    public Response findByEmail(@PathParam("email") String email) {
        LOG.info("Execucao do metodo findByEmail. Email: " + email);
        return Response.ok(clienteService.findByNome(usuarioService.findByEmail(email).getNome())
                .stream()
                .map(ClienteResponseDTO::valueOf)
                .toList()).build();
    }

    @GET
    // @RolesAllowed({"Adm"})
    @Path("/search/cpf/{cpf}")
    public Response findByCpf(@PathParam("cpf") String cpf) {
        LOG.info("Execucao do metodo findByCpf. Cpf: " + cpf);
        return Response.ok(clienteService.findByNome(usuarioService.findByCpf(cpf).getNome())
                .stream()
                .map(ClienteResponseDTO::valueOf)
                .toList()).build();
    }

    @GET
    // @RolesAllowed({"Adm"})
    public Response findAll() {
        LOG.info("Execucao do metodo findAll");
        return Response.ok(clienteService.findAll()
                .stream()
                .map(o -> ClienteResponseDTO.valueOf(o))
                .toList()).build();
    }

    @POST
    public Response create(@Valid ClienteRequestDTO dto) {
        LOG.info("Execucao do metodo create");
        return Response.status(Status.CREATED)
                .entity(ClienteResponseDTO.valueOf(clienteService.create(dto)))
                .build();
    }

    @POST
    @Path("/gerarcliente/{email}")
    public Response gerarClienteFromFuncionario(@PathParam("email") String email) {
        LOG.info("Execucao do metodo gerarClienteFromFuncionario");
        return Response.status(Status.CREATED)
            .entity(ClienteResponseDTO.valueOf(clienteService.gerarClienteFromFuncionario(email)))
            .build();
    }

    @PUT
    @RolesAllowed({"Adm"})
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ClienteRequestDTO cliente) {
        LOG.info("Execucao do metodo update. Id do Cliente: " + id);
        clienteService.update(id, cliente);
        return Response.noContent().build();
    }

    @PATCH
    @RolesAllowed({"Adm"})
    @Path("/{id}/enderecos/{idEndereco}")
    public Response updateEnderecoEspecifico(@PathParam("id") Long id, @PathParam("idEndereco") Long idEndereco, @Valid EnderecoRequestDTO endereco) {
        LOG.info("Execucao do metodo updateEnderecoEspecifico. Id do cliente: " + id + ", id do endereco: " + idEndereco);
        clienteService.updateEnderecoEspecifico(id, idEndereco, endereco);
        return Response.noContent().build();
    }

    @PATCH
    @RolesAllowed({"Adm"})
    @Path("/{id}/enderecos")
    public Response updateEndereco(@PathParam("id") Long id, @Valid List<EnderecoRequestDTO> endereco) {
        LOG.info("Execucao do metodo updateEndereco. Id do cliente: " + id);
        clienteService.updateEndereco(id, endereco);
        return Response.noContent().build();
    }

    @PATCH
    @RolesAllowed({"Adm"})
    @Path("/{id}/telefones/{idTelefone}")
    public Response updateTelefoneEspecifico(@PathParam("id") Long id, @PathParam("idTelefone") Long idTelefone, @Valid TelefoneRequestDTO telefone) {
        LOG.info("Execucao do metodo updateTelefoneEspecifico. Id do cliente: " + id + ", id do telefone: " + idTelefone);
        clienteService.updateTelefoneEspecifico(id, idTelefone, telefone);
        return Response.noContent().build();
    }
    
    @PATCH
    @RolesAllowed({"Adm"})
    @Path("/{id}/telefones")
    public Response updateTelefone(@PathParam("id") Long id, @Valid List<TelefoneRequestDTO> telefone) {
        LOG.info("Execucao do metodo updateTelefone. Id do cliente: " + id);
        clienteService.updateTelefone(id, telefone);
        return Response.noContent().build();
    }

    @DELETE
    @RolesAllowed({"Adm"})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Execucao do metodo delete. Id do cliente: " + id);
        clienteService.delete(id);
        return Response.noContent().build();
    }

    @PATCH
    @RolesAllowed({"Adm"})
    @Path("/{id}/upload/imagem")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@PathParam("id") Long id, @MultipartForm ImageForm form) {
        LOG.info("Execucao do metodo uploadImage. Id do cliente: " + id);

        try {
            String nomeImagem = clienteFileService.save(form.getNomeImagem(), form.getImagem());

            clienteService.updateNomeImagem(id, nomeImagem);
        } catch (IOException e) {
            Response.status(500).build();
        }
        return Response.noContent().build();
    }

    @GET
    @RolesAllowed({"Adm"})
    @Path("/download/imagem/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImage(@PathParam("nomeImagem") String nomeImagem) {
        LOG.info("Execucao do metodo DownloadImage.");
        ResponseBuilder response = Response.ok(clienteFileService.find(nomeImagem));
        response.header("Content-Disposition", "attachment; filename=" + nomeImagem);
        return response.build();
    }

}
