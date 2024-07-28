package diceGamePozoHector.model.repository;

import diceGamePozoHector.model.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
    List<Game> findByPlayer_PlayerId(String id);
    void deleteByPlayer_PlayerId(String playerId);

}
