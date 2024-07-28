package diceGamePozoHector.model.dto;

import diceGamePozoHector.model.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {
    private String userName;
    private String password;
    private Role roles;
}