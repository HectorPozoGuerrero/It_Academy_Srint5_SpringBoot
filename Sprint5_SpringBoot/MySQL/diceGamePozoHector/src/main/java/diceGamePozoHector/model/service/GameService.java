package diceGamePozoHector.model.service;

import diceGamePozoHector.model.dto.GameDTO;

import java.util.List;

public interface GameService {
    GameDTO createGame(Long playerID);
    List<GameDTO> getAllGamesByPlayer(Long playerId);
    void deleteAllGamesByPlayer(Long playerId);
}
