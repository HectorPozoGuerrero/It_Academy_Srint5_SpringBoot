package diceGamePozoHector.model.service.impl;

import diceGamePozoHector.exception.PlayerNotFoundException;
import diceGamePozoHector.model.domain.Game;
import diceGamePozoHector.model.domain.Player;
import diceGamePozoHector.model.domain.User;
import diceGamePozoHector.model.dto.PlayerDTO;
import diceGamePozoHector.model.repository.PlayerRepository;
import diceGamePozoHector.model.service.AuthService;
import diceGamePozoHector.model.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameServiceImpl gameService;
    @Autowired
    private AuthService authService;

    public PlayerDTO convertToDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setId(player.getPlayerId());
        dto.setPlayerName(player.getPlayerName());
        dto.setRegistrationDate(player.getRegistrationDate());
        dto.setWinRatio(player.calculateWinRatio());

        return dto;
    }

    public Player convertToEntity(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setPlayerId(playerDTO.getId());
        player.setPlayerName(playerDTO.getPlayerName());
        player.setRegistrationDate(playerDTO.getRegistrationDate());

        return player;
    }

    public String validateName(String playerName) {
        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Anonymous";
        }/*
        if (playerRepository.existsByNameIgnoreCase(playerName) && !playerName.equalsIgnoreCase("Anonymous")) {
            throw new PlayerAlreadyExistsException("Player with that name already exists.");
        }*/
        return playerName;
    }

    @Override
    public PlayerDTO createPlayer(String playerName, Authentication authentication) {
        List<Game> gameList = new ArrayList<>();
        User userAuth = authService.getUserFromAuthentication(authentication);

        Player newPlayer = new Player();

        String validatedName = validateName(playerName);

        newPlayer.setPlayerName(validatedName);
        newPlayer.setRegistrationDate(LocalDateTime.now());
        newPlayer.setUser(userAuth);

        newPlayer.setGameList(gameList);

        return convertToDTO(playerRepository.save(newPlayer));
    }

    @Override
    public PlayerDTO updatePlayerName(String playerId, String newName) {
        Player playerToUpdate = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException("Player not found by ID: " + playerId));

        String validatedName = validateName(newName);
        playerToUpdate.setPlayerName(validatedName);

        return convertToDTO(playerRepository.save(playerToUpdate));
    }

    @Override
    public PlayerDTO getPlayerById(String id) {
        return convertToDTO(playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException("Player not found by ID: " + id)));
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlayerDTO> getAllPlayersWinRate() {
        List<Player> players = playerRepository.findAll();
        return players.stream()
                .map(player -> new PlayerDTO(
                        player.getPlayerId(),
                        player.getPlayerName(),
                        player.getRegistrationDate(),
                        player.calculateWinRatio()))
                .collect(Collectors.toList());
    }

    @Override
    public double getPlayersRanking() {
        List<Player> players = playerRepository.findAll();

        if (players.isEmpty()) {
            throw new PlayerNotFoundException("No players were found");
        }

        return players.stream()
                .mapToDouble(Player::calculateWinRatio)
                .average()
                .orElse(0.0);
    }

    @Override
    public PlayerDTO getLoser() {
        List<Player> players = playerRepository.findAll();
        Player loser = players.stream()
                .min(Comparator.comparingDouble(Player::calculateWinRatio))
                .orElseThrow(() -> new PlayerNotFoundException("No players were found"));

        return convertToDTO(playerRepository.save(loser));
    }

    @Override
    public PlayerDTO getWinner() {
        List<Player> players = playerRepository.findAll();
        Player winner = players.stream()
                .max(Comparator.comparingDouble(Player::calculateWinRatio))
                .orElseThrow(() -> new PlayerNotFoundException("No players were found"));

        return convertToDTO(playerRepository.save(winner));
    }
}
