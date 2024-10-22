package br.unitins.tp1.roteadores;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.roteadores.dto.QuantidadeAntenaRequestDTO;
import br.unitins.tp1.roteadores.model.QuantidadeAntena;
import br.unitins.tp1.roteadores.service.QuantidadeAntenaService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class QuantidadeAntenaResourceTest {
    
    @Inject
    public QuantidadeAntenaService quantidadeAntenaService;

    @Test
    public void testFindById() {
        given()
            .when().get("/quantidadeantenas/1")
            .then().statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void testFindByQuantidade() {
        given()
            .when().pathParam("quantidade", 2)
            .get("/quantidadeantenas/search/{quantidade}")
            .then().statusCode(200)
            .body("quantidade", hasItem(is(2)));
    }

    @Test
    public void testFindAll() {
        given()
            .when().get("/quantidadeantenas")
            .then().statusCode(200);
    }

    @Test
    public void testCreate() {
        QuantidadeAntenaRequestDTO dto = new QuantidadeAntenaRequestDTO(7);

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post("/quantidadeantenas")
            .then()
                .statusCode(201)
                .body("quantidade", is(7));
                
        // removendo o dado que foi inserido
        quantidadeAntenaService.delete(quantidadeAntenaService.findByQuantidade(7).getFirst().getId());
    }

    @Test
    public void testUpdate() {
        QuantidadeAntenaRequestDTO dto = new QuantidadeAntenaRequestDTO(7);
        long id = quantidadeAntenaService.create(dto).getId();

        QuantidadeAntenaRequestDTO novoDto = new QuantidadeAntenaRequestDTO(8);

        given()
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
                .put("/quantidadeantenas/" + id)
            .then()
                .statusCode(204);
        
        QuantidadeAntena quantidadeAntena = quantidadeAntenaService.findById(id);

        assertEquals(quantidadeAntena.getQuantidade(), 8);

        quantidadeAntenaService.delete(id);

    }  

    @Test
    public void testDelete() {
        QuantidadeAntenaRequestDTO dto = new QuantidadeAntenaRequestDTO(7);
        Long id = quantidadeAntenaService.create(dto).getId();

        given()
            .when()
                .delete("/quantidadeantenas/" + id)
            .then().statusCode(204);

        QuantidadeAntena quantidadeAntena = quantidadeAntenaService.findById(id);
        assertNull(quantidadeAntena);

    }
    


}