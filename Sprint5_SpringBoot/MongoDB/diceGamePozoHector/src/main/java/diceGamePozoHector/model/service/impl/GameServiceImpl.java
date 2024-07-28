package diceGamePozoHector.model.service.impl;

import diceGamePozoHector.exception.PlayerNotFoundException;
import diceGamePozoHector.model.domain.Game;
import diceGamePozoHector.model.domain.Player;
import diceGamePozoHector.model.dto.GameDTO;
import diceGamePozoHector.model.repository.GameRepository;
import diceGamePozoHector.model.repository.PlayerRepository;
import diceGamePozoHector.model.service.GameService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public int diceRoll(){
        Random diceRoll = new Random();
        return diceRoll.nextInt(6) + 1;
    }
    public GameDTO convertToDto(Game game){
        GameDTO dto = new GameDTO();
        dto.setId(game.getGameId());
        dto.setDiceRoll1(game.getDiceRoll1());
        dto.setDiceRoll2(game.getDiceRoll2());
        dto.setPlayerName(game.getPlayer().getPlayerName());
        dto.setWon(game.isWon());

        return dto;
    }

    public Game convertToEntity(GameDTO dto){
        Game game = new Game();
        game.setGameId(dto.getId());
        game.setDiceRoll1(dto.getDiceRoll1());
        game.setDiceRoll2(dto.getDiceRoll2());
        game.setWon(dto.isWon());

        return game;
    }

    @Override
    public GameDTO createGame(String playerID) {
        Game newGame = new Game();
        Player player = playerRepository.findById(playerID).orElseThrow(() -> new PlayerNotFoundException("Player not found by ID: " +  playerID));

        int diceRoll1 = diceRoll();
        int diceRoll2 = diceRoll();

        newGame.setDiceRoll1(diceRoll1);
        newGame.setDiceRoll2(diceRoll2);

        newGame.setWon(newGame.isWon());
        newGame.setGameDate(new Date());
        newGame.setPlayer(player);

        player.addGame(newGame);

        return convertToDto(gameRepository.save(newGame));
    }


    @Override
    public List<GameDTO> getAllGamesByPlayer(String playerId) {

        Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException("Player not found by ID: " +playerId));

        List<Game> playerGames = gameRepository.findByPlayer_PlayerId(playerId);

        return playerGames.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllGamesByPlayer(String playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException("Player not found by ID: " +playerId));
        gameRepository.deleteByPlayer_PlayerId(playerId);

    }
}
