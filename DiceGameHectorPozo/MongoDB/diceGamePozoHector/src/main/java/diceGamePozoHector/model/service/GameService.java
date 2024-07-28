package diceGamePozoHector.model.service;

import diceGamePozoHector.model.dto.GameDTO;

import java.util.List;

public interface GameService {
    GameDTO createGame(String playerID);
    List<GameDTO> getAllGamesByPlayer(String playerId);
    void deleteAllGamesByPlayer(String playerId);
}
