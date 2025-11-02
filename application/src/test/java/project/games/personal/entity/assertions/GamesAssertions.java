package project.games.personal.entity.assertions;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import project.games.personal.dto.GameMinDTO;
import project.games.personal.entities.Games;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GamesAssertions {

    private final Games games;


    public static GamesAssertions assertGames(Games entity) {
        return new GamesAssertions(spy(entity));
    }

    public void returnGameWithAllAtributes(){
        assertThat(games.getId())
                .isEqualTo(1L);
        assertThat(games.getTitle())
                .isEqualTo("The Legend of Java");
        assertThat(games.getYear())
                .isEqualTo(2024);
        assertThat(games.getGenre())
                .isEqualTo("RPG");
        assertThat(games.getScore())
                .isEqualTo(4.9);
        assertThat(games.getPlatforms())
                .isEqualTo("PC, PS5");
        assertThat(games.getImgUrl())
                .isEqualTo("http://example.com/image.jpg");
        assertThat(games.getShortDescription())
                .isEqualTo("Uma breve descrição do jogo.");
        assertThat(games.getLongDescription())
                .isEqualTo("Uma descrição bem mais longa e detalhada.");
    }

}
