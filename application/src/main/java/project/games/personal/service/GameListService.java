package project.games.personal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.games.personal.dto.GameListDTO;
import project.games.personal.dto.ReplacementDTO;
import project.games.personal.entities.GameList;
import project.games.personal.projections.GameMinProjection;
import project.games.personal.repository.GameListRepository;
import project.games.personal.repository.GameRepository;

import java.util.List;

@Service
public class GameListService {
    @Autowired
    private GameListRepository gameListRepository;
    @Autowired
    private GameRepository gameRepository;


    @Transactional(readOnly = true)
    public List<GameList> findAll() {

        return gameListRepository.findAll();

    }

    @Transactional
    public void movePosition(Long listId, ReplacementDTO dto){


        List<GameMinProjection> orderedGames = gameRepository.searchByList(listId);

        GameMinProjection movedGame = orderedGames.remove(dto.getSourceIndex());
        orderedGames.add(dto.getDestinationIndex(), movedGame);

        updatePositions(listId, orderedGames, dto.getSourceIndex(), dto.getDestinationIndex());
        }

    private void updatePositions(Long listId, List<GameMinProjection> games, int sourceIndex, int destinationIndex) {
        int min = Math.min(sourceIndex, destinationIndex);
        int max = Math.max(sourceIndex, destinationIndex);

        for (int i = min; i <= max; i++) {
            gameListRepository.updateBelongingPosition(listId, games.get(i).getId(), i);
        }

    }
}

