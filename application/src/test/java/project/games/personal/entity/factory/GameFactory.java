package project.games.personal.entity.factory;


import project.games.personal.entities.Games;
import project.games.personal.projections.GameMinProjection;

public final class GameFactory {

    private GameFactory(){}

    public static Games criarGameComTodosOsCampos() {

        return Games.builder()
                .id(1L)
                .title("The Legend of Java")
                .year(2024)
                .genre("RPG")
                .score(4.9)
                .platforms("PC, PS5")
                .imgUrl("http://example.com/image.jpg")
                .shortDescription("Uma breve descrição do jogo.")
                .longDescription("Uma descrição bem mais longa e detalhada.")
                .build();

    }

    public static GameMinProjection criarGameProjectionCompleto() {

        return new GameMinProjection() {
            @Override
            public Long getId() {
                return 1L;
            }

            @Override
            public String getTitle() {
                return "Salve";
            }

            @Override
            public Integer getGameYear() {
                return 2002;
            }

            @Override
            public String getImgUrl() {
                return "Salve";
            }

            @Override
            public String getShortDescription() {
                return "Opa";
            }
        };
    }
}
