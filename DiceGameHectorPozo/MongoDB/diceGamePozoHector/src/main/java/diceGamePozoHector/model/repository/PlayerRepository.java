package diceGamePozoHector.model.repository;


import diceGamePozoHector.model.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends MongoRepository<Player, Long> {
   // boolean existsByNameIgnoreCase(String playerName);
   void deleteByPlayerName(String playerName);

   Optional<Player> findById(String playerId);
}
