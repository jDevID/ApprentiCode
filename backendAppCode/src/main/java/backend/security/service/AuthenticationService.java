package backend.security.service;

import backend.domain.entity.User;
import backend.security.config.jwt.JwtService;
import backend.security.domain.entity.RefreshToken;
import backend.security.domain.entity.Role;
import backend.security.domain.request.AuthenticationRequest;
import backend.security.domain.request.RegisterRequest;
import backend.security.domain.response.AuthenticationResponse;
import backend.security.repository.RefreshTokenRepository;
import backend.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService; // to generate token
    private final AuthenticationManager authenticationManager; // to authenticate after registering
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserDetailsService userDetailsService;

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
        log.info("User registered successfully: {}", user.getEmail());
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
        log.info("User authenticated successfully: {}", user.getEmail());
        return AuthenticationResponse.builder()
                .token(jwtToken) //pass it the token
                .build();
    }

    public AuthenticationResponse refreshToken(String refreshToken) {
        RefreshToken refreshTokenEntity = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        if (refreshTokenEntity.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshTokenEntity);
            throw new RuntimeException("Refresh token expired");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(refreshTokenEntity.getUser().getEmail());
        String jwt = jwtService.generateToken(userDetails);

        return new AuthenticationResponse(jwt, refreshToken);
    }

    private RefreshToken generateRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plus(7, ChronoUnit.DAYS));

        refreshTokenRepository.deleteByUser(user);
        return refreshTokenRepository.save(refreshToken);
    }
}
