package dev.id.backend.logic.security.services;

import dev.id.backend.logic.security.models.entities.Role;
import dev.id.backend.logic.security.configs.jwt.JwtService;
import dev.id.backend.logic.security.models.requests.AuthenticationRequest;
import dev.id.backend.logic.security.models.requests.RegisterRequest;
import dev.id.backend.logic.security.models.response.AuthenticationResponse;
import dev.id.backend.logic.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor @Slf4j
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService; // to generate token
    private final AuthenticationManager authenticationManager; // to authenticate after registering
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);


    public AuthenticationResponse register(RegisterRequest request) { // Create a User Object
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // PasswordEncoder
                .role(Role.USER)
                .build();
        repository.save(user); // !

        var jwtToken = jwtService.generateToken(user);
        logger.info("User registered successfully: {}", user.getEmail());
        return AuthenticationResponse.builder()
                .token(jwtToken) //pass it the token
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        // At this point => user is authenticated
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(); // add an Exception !

        // generate token and return Response
        var jwtToken = jwtService.generateToken(user);
        logger.info("User authenticated successfully: {}", user.getEmail());
        return AuthenticationResponse.builder()
                .token(jwtToken) //pass it the token
                .build();
    }

}
