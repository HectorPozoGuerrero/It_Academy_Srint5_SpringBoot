package diceGamePozoHector.model.repository;


import diceGamePozoHector.model.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
   // boolean existsByNameIgnoreCase(String playerName);
   void deleteByPlayerName(String playerName);
}
