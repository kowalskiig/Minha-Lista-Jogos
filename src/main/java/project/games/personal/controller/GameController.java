package project.games.personal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.games.personal.dto.GameDTO;
import project.games.personal.dto.GameMinDTO;
import project.games.personal.entities.Games;
import project.games.personal.mapper.GameMapper;
import project.games.personal.service.GameService;

import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GameController {
    @Autowired
    private GameService gameService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<GameDTO> findByID(@PathVariable Long id){
        Games result = gameService.findById(id);
        return ResponseEntity.ok(GameMapper.entityToFullDto(result));
    }

    @GetMapping
    public ResponseEntity<List<GameMinDTO>> findAll(){
        List<GameMinDTO> result = gameService.findAll();
        return ResponseEntity.ok(result);

    }
}
