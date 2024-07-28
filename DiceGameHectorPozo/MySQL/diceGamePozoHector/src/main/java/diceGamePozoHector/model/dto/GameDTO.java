package diceGamePozoHector.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {

    private long id;
    private int diceRoll1;
    private int diceRoll2;
    private String playerName;
    private boolean won;
}
