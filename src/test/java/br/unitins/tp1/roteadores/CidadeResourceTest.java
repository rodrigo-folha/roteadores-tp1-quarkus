package br.unitins.tp1.roteadores;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.roteadores.dto.CidadeRequestDTO;
import br.unitins.tp1.roteadores.model.Cidade;
import br.unitins.tp1.roteadores.resource.CidadeResource;
import br.unitins.tp1.roteadores.service.CidadeService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class CidadeResourceTest {
    
    @Inject
    public CidadeService cidadeService;

    @Test
    public void testFindById() {
        given()
            .when().get("/cidades/1")
            .then().statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void testFindByNome() {
        given()
            .when().pathParam("nome", "Palmas")
            .get("/cidades/search/{nome}")
            .then().statusCode(200)
            .body("nome", hasItem(is("Palmas")));
    }

    @Test
    public void testFindAll() {
        given()
            .when().get("/cidades")
            .then().statusCode(200);
    }

    @Test
    public void testCreate() {
        CidadeRequestDTO dto = new CidadeRequestDTO("Tocantinopolis", 1l);

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post("/cidades")
            .then()
                .statusCode(201)
                .body("nome", is("Tocantinopolis"),
                      "estado.nome", is("Tocantins"),
                      "estado.sigla", is("TO"));
                
        // removendo o dado que foi inserido
        cidadeService.delete(cidadeService.findByNome("Tocantinopolis").getFirst().getId());
    }

    @Test
    public void testUpdate() {
        CidadeRequestDTO dto = new CidadeRequestDTO("Muricilandia", 1l);
        long id = cidadeService.create(dto).getId();

        CidadeRequestDTO novoDto = new CidadeRequestDTO("Maurilandia", 1l);

        given()
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
                .put("/cidades/" + id)
            .then()
                .statusCode(204);
        
        Cidade cidade = cidadeService.findById(id);

        assertEquals(cidade.getNome(), "Maurilandia");

        cidadeService.delete(id);

    }  

    @Test
    public void testDelete() {
        CidadeRequestDTO dto = new CidadeRequestDTO("Muricilandia", 1l);
        Long id = cidadeService.create(dto).getId();
    
        given()
            .when()
                .delete("/cidades/" + id)
            .then().statusCode(204);

        Cidade cidade = cidadeService.findById(id);
        assertNull(cidade);

    }

    @Test
    @TestHTTPEndpoint(CidadeResource.class)
    public void testFindAll2(){
        given()
            .when().get()
            .then()
                .statusCode(200)
                .body("$.size()", greaterThan(1),
                     "[1].nome", is("Paraiso"));
    }
    


}
