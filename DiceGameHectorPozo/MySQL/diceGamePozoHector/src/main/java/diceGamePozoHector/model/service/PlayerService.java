package diceGamePozoHector.model.service;

import diceGamePozoHector.model.dto.PlayerDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface PlayerService {
    PlayerDTO createPlayer(String playerName, Authentication authentication);
    PlayerDTO updatePlayerName(Long playerId, String newName);
    PlayerDTO getPlayerById(Long id);
    List<PlayerDTO> getAllPlayers();
    List<PlayerDTO> getAllPlayersWinRate();
    void deletePlayerById(Long id);
    double getPlayersRanking();
    PlayerDTO getLoser();
    PlayerDTO getWinner();
}
