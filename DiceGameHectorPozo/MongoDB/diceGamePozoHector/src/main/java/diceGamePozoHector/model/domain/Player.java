package diceGamePozoHector.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private String playerId;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @OneToMany(mappedBy = "player")
    private List<Game> gameList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void addGame(Game game) {
        if (gameList == null) {
            gameList = new ArrayList<>();
        }
        gameList.add(game);
        game.setPlayer(this);
    }

    public double calculateWinRatio() {
        if (gameList.isEmpty()) {
            return 0.0;
        }
        double wins = gameList.stream().filter(Game::isWon).count();
        return (wins / gameList.size() * 100);
    }
}
