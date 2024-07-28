package diceGamePozoHector.controller;


import diceGamePozoHector.model.dto.PlayerDTO;
import diceGamePozoHector.model.service.impl.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerServiceImpl playerService;

    @PostMapping("/")
    public ResponseEntity<PlayerDTO> addPlayer(@RequestBody PlayerDTO playerDTO, Authentication authentication){
        PlayerDTO savedPlayer = playerService.createPlayer(playerDTO.getPlayerName(), authentication);
        return ResponseEntity.ok(savedPlayer);
    }

    @PutMapping("/")
    public ResponseEntity<PlayerDTO> updatePlayer(@RequestBody PlayerDTO playerDTO){
        PlayerDTO updatedPlayer = playerService.updatePlayerName(playerDTO.getId(), playerDTO.getPlayerName());
        return ResponseEntity.ok(updatedPlayer);
    }

    @GetMapping("/")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers(){
        List<PlayerDTO> players = playerService.getAllPlayersWinRate();
        return ResponseEntity.ok(players);
    }

    @GetMapping("/ranking")
    public ResponseEntity<Double> getAverageWinRatio(){
        double averageWinRatio = playerService.getPlayersRanking();
        return ResponseEntity.ok(averageWinRatio);
    }

    @GetMapping("/ranking/loser")
    public ResponseEntity<PlayerDTO> worstWinRatio(){
        PlayerDTO worstPlayer = playerService.getLoser();
        return ResponseEntity.ok(worstPlayer);
    }

    @GetMapping("/ranking/winner")
    public ResponseEntity<PlayerDTO> bestWinRatio(){
        PlayerDTO bestPlayer = playerService.getWinner();
        return ResponseEntity.ok(bestPlayer);
    }
}
