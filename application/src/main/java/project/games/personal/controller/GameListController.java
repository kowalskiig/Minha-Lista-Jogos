package project.games.personal.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.games.personal.dto.GameListDTO;
import project.games.personal.dto.GameMinDTO;
import project.games.personal.dto.ReplacementDTO;
import project.games.personal.entities.GameList;
import project.games.personal.mapper.GameMapper;
import project.games.personal.service.GameListService;
import project.games.personal.service.GameService;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

    @Autowired
    private GameListService gameListService;
    @Autowired
    private GameService gameService;

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<GameListDTO>> findAll() {

        List<GameList> gameList = gameListService.findAll();

        List<GameListDTO> response = gameList.stream().map(x -> new GameListDTO(x)).toList();

        return ResponseEntity.ok(response);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{listId}/games")

    public ResponseEntity<List<GameMinDTO>> findByList(@PathVariable Long listId) {

        List<GameMinDTO> result = gameService.findByList(listId);
        return ResponseEntity.ok(result);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping(value = "/{listId}/replacement")
    public ResponseEntity<Void> replacePosition(@PathVariable Long listId, @Valid @RequestBody ReplacementDTO source) {
        gameListService.movePosition(listId, source);
        return ResponseEntity.noContent().build();
    }


}
