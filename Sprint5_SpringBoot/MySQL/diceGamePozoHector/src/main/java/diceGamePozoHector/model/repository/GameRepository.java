package diceGamePozoHector.model.repository;

import diceGamePozoHector.model.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByPlayer_PlayerId(Long id);
    void deleteByPlayer_PlayerId(Long playerId);

}
