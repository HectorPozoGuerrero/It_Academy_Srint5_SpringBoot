package diceGamePozoHector.model.service.impl;

import diceGamePozoHector.model.dto.LoginDTO;
import diceGamePozoHector.model.domain.Role;
import diceGamePozoHector.model.domain.User;
import diceGamePozoHector.model.dto.response.AuthResponseDTO;
import diceGamePozoHector.model.dto.UserRegistrationDTO;
import diceGamePozoHector.model.repository.UserRepository;
import diceGamePozoHector.model.service.AuthService;
import diceGamePozoHector.model.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDTO register(UserRegistrationDTO userRegistrationDTO){
        var user = User.builder()
                .userName(userRegistrationDTO.getUserName())
                .password(passwordEncoder.encode(userRegistrationDTO.getPassword()))
                .roles(Role.PLAYER)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthResponseDTO.builder().token(jwtToken).build();
    }

    @Override
    public AuthResponseDTO authenticate(LoginDTO loginDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUserName(),
                        loginDTO.getPassword()
                )
        );

        var user = userRepository.findUserByUserName(loginDTO.getUserName()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthResponseDTO.builder().token(jwtToken).build();
    }

    @Override
    public User getUserFromAuthentication(Authentication authentication){
        String userName = authentication.getName();
        return userRepository.findUserByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by name: " + userName));
    }
}
