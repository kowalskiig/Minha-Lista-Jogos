package project.games.personal.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import project.games.personal.dto.GameMinDTO;
import project.games.personal.entity.assertions.GamesAssertions;
import project.games.personal.entity.factory.GameFactory;
import project.games.personal.exception.ResourceNotFoundException;
import project.games.personal.projections.GameMinProjection;
import project.games.personal.repository.GameRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static project.games.personal.entity.factory.GameFactory.criaGameMinProjection;
import static project.games.personal.entity.factory.GameFactory.criarGameComTodosOsCampos;

@ExtendWith(MockitoExtension.class)
public class GameServiceUnitTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    GameRepository gameRepository;

    @DisplayName("Quando acessar método findById")
    @Nested
    class findById {

        @DisplayName("Quando executar com sucesso")
        @Nested
        class Sucesso {

            @DisplayName("Retornar Game com todos os atributos")
            @Test
            void test1(){

                // dado
            var idJogoExistente = 1L;

            var jogoExistente = criarGameComTodosOsCampos();

            Mockito.when(gameRepository.findById(idJogoExistente))
                        .thenReturn(Optional.of(jogoExistente));

                // quando

                var games = gameService
                        .findById(idJogoExistente);

                //validacao

                GamesAssertions.assertGames(games)
                        .returnGameWithAllAtributes();

            }
        }

        @DisplayName("Quando executar com falha")
        @Nested
        class Falha{

            @DisplayName("Quando Id não existir, retornar ResourceNotFoundException")
            @Test
            void test2(){
                // dados

                var idInexistente = 2L;

                Mockito.when(gameRepository.findById(idInexistente))
                        .thenReturn(Optional.empty());

                //quando

                ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
                    gameService
                            .findById(idInexistente);
                });

                //entao

                assertThat(exception.getMessage())
                        .isEqualTo("Id " + idInexistente + " does not correspond to any game");


            }

        }


    }

    @DisplayName("Quando acessar o método findByList")
    @Nested
    class findByList{

        @DisplayName("Executar com sucesso")
        @Nested
        class Sucesso{

            @DisplayName("Quando id da lista existir, retorna Lista de GameMinDTO com todos os campos")
            @Test
            void teste1(){

                //dado

                var idExistenteListGame = 1L;

                var gameProjection = criaGameMinProjection();

                List<GameMinProjection> listGameMinProjection = new ArrayList<>();
                listGameMinProjection.add(gameProjection);

                Mockito.when(gameRepository.searchByList(idExistenteListGame))
                        .thenReturn(listGameMinProjection);

                //quando

                var result = gameService.findByList(idExistenteListGame);

                //entao

                assertThat(result.getFirst().getId())
                        .isEqualTo(gameProjection.getId());

                assertThat(result.getFirst().getTitle())
                        .isEqualTo(gameProjection.getTitle());

                assertThat(result.size())
                        .isEqualTo(1);

            }

        }

    }

}
