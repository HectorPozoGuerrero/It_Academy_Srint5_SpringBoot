package diceGamePozoHector.model.service;

import diceGamePozoHector.model.dto.PlayerDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface PlayerService {
    PlayerDTO createPlayer(String playerName, Authentication authentication);
    PlayerDTO updatePlayerName(String playerId, String newName);
    PlayerDTO getPlayerById(String id);
    List<PlayerDTO> getAllPlayers();
    List<PlayerDTO> getAllPlayersWinRate();
    double getPlayersRanking();
    PlayerDTO getLoser();
    PlayerDTO getWinner();
}
