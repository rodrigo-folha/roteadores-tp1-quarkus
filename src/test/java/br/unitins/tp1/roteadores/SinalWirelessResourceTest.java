package br.unitins.tp1.roteadores;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.roteadores.dto.SinalWirelessRequestDTO;
import br.unitins.tp1.roteadores.model.SinalWireless;
import br.unitins.tp1.roteadores.service.SinalWirelessService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class SinalWirelessResourceTest {
    
    @Inject
    public SinalWirelessService sinalWirelessService;

    @Test
    public void testFindById() {
        given()
            .when().get("/sinalwireless/1")
            .then().statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void testFindByNome() {
        given()
            .when().pathParam("nome", "Wi-Fi 5")
            .get("/sinalwireless/search/{nome}")
            .then().statusCode(200)
            .body("nome", hasItem(is("Wi-Fi 5")));
    }

    @Test
    public void testFindAll() {
        given()
            .when().get("/sinalwireless")
            .then().statusCode(200);
    }

    @Test
    public void testCreate() {
        SinalWirelessRequestDTO dto = new SinalWirelessRequestDTO("Wi-Fi 7");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post("/sinalwireless")
            .then()
                .statusCode(201)
                .body("nome", is("Wi-Fi 7"));
                
        // removendo o dado que foi inserido
        sinalWirelessService.delete(sinalWirelessService.findByNome("Wi-Fi 7").getFirst().getId());
    }

    @Test
    public void testUpdate() {
        SinalWirelessRequestDTO dto = new SinalWirelessRequestDTO("Wi-Fi 7");
        long id = sinalWirelessService.create(dto).getId();

        SinalWirelessRequestDTO novoDto = new SinalWirelessRequestDTO("Wi-Fi 7");

        given()
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
                .put("/sinalwireless/" + id)
            .then()
                .statusCode(204);
        
        SinalWireless sinalWireless = sinalWirelessService.findById(id);

        assertEquals(sinalWireless.getNome(), "Wi-Fi 7");

        sinalWirelessService.delete(id);

    }  

    @Test
    public void testDelete() {
        SinalWirelessRequestDTO dto = new SinalWirelessRequestDTO("Wi-Fi 7");
        Long id = sinalWirelessService.create(dto).getId();

        given()
            .when()
                .delete("/sinalwireless/" + id)
            .then().statusCode(204);

        SinalWireless sinalWireless = sinalWirelessService.findById(id);
        assertNull(sinalWireless);

    }
    


}
