package project.games.personal.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;
import project.games.personal.PersonalApplication;
import project.games.personal.controller.setup.PostgresSetup;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = PersonalApplication.class)
@Testcontainers
@Transactional
public class GameServiceIT implements PostgresSetup {

    @LocalServerPort
    Integer port;

    @BeforeEach
    void beforeEach() {
        RestAssured.baseURI = String.format("http://localhost:%s", port);
    }


    @DisplayName("Quando acessar endpoint findById")
    @Nested
    class findById{

        @DisplayName("Deve retornar 200 e entidade")
        @Nested
        class Sucesso{

            @DisplayName("Deve retornar 200, quando id de Games existir na base")
            @Test
            void teste1(){
                //dados
            var idExistente = 1L;

            //quando
            given()
                    .when()
                    .get("/games/{id}", idExistente)

            //entao
                .then()
                    .statusCode(200)
                    .body("id", is(1));
            }
        }

        @DisplayName("Quando cenário de erro")
        @Nested
        class Erro{

            @DisplayName("Deve retornar 404 quando id não existir na base")
            @Test
            void test2(){

                //dado
                var gameIdInexistente = 2000L;

                //quando
                given()

                        .get("/games/{id}", gameIdInexistente)
                //entao
                        .then()
                        .statusCode(404);

            }

        }

    }


}




