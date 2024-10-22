package br.unitins.tp1.roteadores;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.roteadores.dto.RoteadorRequestDTO;
import br.unitins.tp1.roteadores.model.Roteador;
import br.unitins.tp1.roteadores.service.RoteadorService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class RoteadorResourceTest {
    
    @Inject
    public RoteadorService roteadorService;

    @Test
    public void testFindById() {
        given()
            .when().get("/roteadores/1")
            .then().statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void testFindByNome() {
        given()
            .when().pathParam("nome", "ROT WIFI GIGA")
            .get("/roteadores/search/{nome}")
            .then().statusCode(200)
            .body("nome", hasItem(is("ROT WIFI GIGA AC1200MBPS 4ANT Tenda AC8")));
    }

    @Test
    public void testFindBySinalWireless() {
        given()
            .when().pathParam("id", 1)
            .get("/roteadores/search/sinalwireless/{id}")
            .then().statusCode(200)
            .body("sinalWireless.id", everyItem(is(1)),
                  "sinalWireless.nome", everyItem(is("Wi-Fi 5")));
    }

    @Test
    public void testFindBySistemaOperacional() {
        given()
            .when().pathParam("id", 3)
            .get("/roteadores/search/sistemasoperacionais/{id}")
            .then().statusCode(200)
            .body("sistemaOperacional.id", everyItem(is(3)),
                  "sistemaOperacional.nome", everyItem(is("RouterOS")));
    }

    @Test
    public void testFindByQuantidadeAntena() {
        given()
            .when().pathParam("id", 1)
            .get("/roteadores/search/quantidadeantenas/{id}")
            .then().statusCode(200)
            .body("quantidadeAntena.id", everyItem(is(1)),
                  "quantidadeAntena.quantidade", everyItem(is(1)));
    }

    @Test
    public void testFindByBandaFrequencia() {
        given()
            .when().pathParam("id", 1)
            .get("/roteadores/search/bandafrequencias/{id}")
            .then().statusCode(200)
            .body("bandaFrequencia.id", everyItem(is(1)),
                  "bandaFrequencia.nome", everyItem(is("Single-Band")));
    }

    @Test
    public void testFindByProtocoloSeguranca() {
        given()
            .when().pathParam("id", 7)
            .get("/roteadores/search/protocolosseguranca/{id}")
            .then().statusCode(200)
            .body("protocoloSeguranca.id", everyItem(is(7)),
                  "protocoloSeguranca.nome", everyItem(is("WPS")));
    }

    @Test
    public void testFindByPreco() {
        given()
            .when()
                .pathParam("min", 500)
                .pathParam("max", 1000)
            .get("/roteadores/search/preco/{min}/{max}")
            .then().statusCode(200)
            .body("preco", everyItem(greaterThanOrEqualTo(500f)),
                  "preco", everyItem(lessThanOrEqualTo(1000f)),
                  "nome", hasItem(is("Roteador de Firewall Cisco RV110W-A-NA-K9 Small Business RV110W Wireless N VPN")));
    }


    @Test
    public void testFindAll() {
        given()
            .when().get("/roteadores")
            .then().statusCode(200);
    }

    @Test
    public void testCreate() {
        RoteadorRequestDTO dto = new RoteadorRequestDTO(
            "Roteador teste 1",
            "Roteador teste 1 descricao",
            1522.90,
            1l,
            1l,
            1l,
            1l,
            1l
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post("/roteadores")
            .then()
                .statusCode(201)
                .body("nome", is("Roteador teste 1"),
                      "descricao", is("Roteador teste 1 descricao"),
                      "preco", is(1522.90f),
                      "sinalWireless.id", is(1),
                      "sistemaOperacional.id", is(1),
                      "bandaFrequencia.id", is(1),
                      "protocoloSeguranca.id", is(1),
                      "quantidadeAntena.id", is(1));
                
        // removendo o dado que foi inserido
        roteadorService.delete(roteadorService.findByNome("Roteador teste 1").getFirst().getId());
    }

    @Test
    public void testUpdate() {
        RoteadorRequestDTO dto = new RoteadorRequestDTO(
            "Roteador teste 1",
            "Roteador teste 1 descricao",
            1522.90,
            1l,
            1l,
            1l,
            1l,
            1l
        );

        long id = roteadorService.create(dto).getId();

        RoteadorRequestDTO novoDto = new RoteadorRequestDTO(
            "Roteador teste 2",
            "Roteador teste 2 descricao",
            1222.90,
            2l,
            2l,
            2l,
            2l,
            2l
        );

        given()
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
                .put("/roteadores/" + id)
            .then()
                .statusCode(204);
        
        Roteador roteador = roteadorService.findById(id);

        assertEquals(roteador.getNome(), "Roteador teste 2");
        assertEquals(roteador.getDescricao(), "Roteador teste 2 descricao");
        assertEquals(roteador.getPreco(), 1222.90);
        assertEquals(roteador.getSinalWireless().getId(), 2);
        assertEquals(roteador.getSistemaOperacional().getId(), 2);
        assertEquals(roteador.getBandaFrequencia().getId(), 2);
        assertEquals(roteador.getProtocoloSeguranca().getId(), 2);
        assertEquals(roteador.getQuantidadeAntena().getId(), 2);

        roteadorService.delete(id);

    }  

    @Test
    public void testDelete() {
        RoteadorRequestDTO dto = new RoteadorRequestDTO(
            "Roteador teste 1",
            "Roteador teste 1 descricao",
            1522.90,
            1l,
            1l,
            1l,
            1l,
            1l
        );

        Long id = roteadorService.create(dto).getId();

        given()
            .when()
                .delete("/roteadores/" + id)
            .then().statusCode(204);

        Roteador roteador = roteadorService.findById(id);
        assertNull(roteador);

    }

    

}
