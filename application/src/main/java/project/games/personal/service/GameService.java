package project.games.personal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.games.personal.dto.GameDTO;
import project.games.personal.dto.GameMinDTO;
import project.games.personal.entities.Games;
import project.games.personal.exception.ResourceNotFoundException;
import project.games.personal.mapper.GameMapper;
import project.games.personal.projections.GameMinProjection;
import project.games.personal.repository.GameRepository;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public Games findById(Long id){

        return gameRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Id " + id + " does not correspond to any game"));
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId) {

        List<GameMinProjection> result = gameRepository.searchByList(listId);

        return result.stream().map(x -> (GameMapper.projectionToMinDto(x))).toList();


    }

    @Transactional(readOnly = true)
    public List<Games> findAll() {

        return gameRepository.findAll();
    }
}






