package project.games.personal.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import project.games.personal.dto.ReplacementDTO;
import project.games.personal.entities.GameList;
import project.games.personal.entities.Games;
import project.games.personal.entity.factory.GameFactory;
import project.games.personal.projections.GameMinProjection;
import project.games.personal.repository.GameListRepository;
import project.games.personal.repository.GameRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class GameListServiceTest {

    @InjectMocks
    GameListService gameListService;

    @Mock
    GameRepository gameRepository;

    @Mock
    GameListRepository gameListRepository;

    @DisplayName("Quando acessar método movePosition")
    @Nested
    class movePosition {

        @DisplayName("Deve executar com sucesso com jogos trocados de posição")
        @Nested
        class Sucesso {

            @DisplayName("Quando Id da lista de jogos for existente e duas posição forem diferentes e existentes")
            @Test
            void test1() {

                // dado

                //Lista padrão

                Long gameListId = 1L;

                GameMinProjection games1= GameFactory.criarGameProjectionCompleto();
                GameMinProjection games2= GameFactory.criarGameProjectionCompleto();
                GameMinProjection games3= GameFactory.criarGameProjectionCompleto();

                List<GameMinProjection> gameList = new ArrayList<>();
                gameList.add(games1);
                gameList.add(games2);
                gameList.add(games3);

                Mockito.when(gameRepository.searchByList(gameListId))
                        .thenReturn(gameList);

                Mockito.doNothing()
                        .when(gameListRepository)
                        .updateBelongingPosition(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyInt());

                ReplacementDTO dto = new ReplacementDTO(0,1);

                // quando

                gameListService.movePosition(gameListId, dto);

                // validação

                Mockito.verify(gameRepository).searchByList(gameListId);
                Mockito.verify(gameListRepository, Mockito.atLeastOnce())
                        .updateBelongingPosition(Mockito.eq(gameListId), Mockito.anyLong(), Mockito.anyInt());

                assertThat(gameList.size())
                        .isEqualTo(3);
                assertThat(gameList.get(dto.getDestinationIndex()))
                        .isNotNull();
                assertThat(gameList.get(1))
                        .isEqualTo(games1);
            }

            }
        }
    }



