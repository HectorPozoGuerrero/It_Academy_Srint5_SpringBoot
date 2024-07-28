package diceGamePozoHector.model.service;

import diceGamePozoHector.model.dto.LoginDTO;
import diceGamePozoHector.model.domain.User;
import diceGamePozoHector.model.dto.response.AuthResponseDTO;
import diceGamePozoHector.model.dto.UserRegistrationDTO;
import org.springframework.security.core.Authentication;

public interface AuthService {
    AuthResponseDTO register(UserRegistrationDTO userRegistrationDTO);
    AuthResponseDTO authenticate(LoginDTO loginDTO);
    User getUserFromAuthentication(Authentication authentication);
}