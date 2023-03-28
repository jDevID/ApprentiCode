package backend.security.service;

import backend.security.repository.UserRepository;
import backend.security.config.jwt.JwtService;
import backend.security.domain.entity.Role;
import backend.security.domain.request.AuthenticationRequest;
import backend.security.domain.request.RegisterRequest;
import backend.security.domain.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import backend.domain.entity.User;
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
