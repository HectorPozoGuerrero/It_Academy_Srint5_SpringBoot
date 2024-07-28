package diceGamePozoHector.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private long id;
    private String playerName;
    private LocalDateTime registrationDate;
    private double winRatio;
}
