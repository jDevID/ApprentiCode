package dev.id.backend.logic.security.controller;


import dev.id.backend.logic.security.models.request.AuthenticationRequest;
import dev.id.backend.logic.security.models.request.RegisterRequest;
import dev.id.backend.logic.security.models.response.AuthenticationResponse;
import dev.id.backend.logic.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<dev.id.backend.logic.security.models.response.AuthenticationResponse> register (
            @RequestBody RegisterRequest request
    ) {
        // delegate to Service
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register (
            @RequestBody AuthenticationRequest request
    ) {
        // delegate to Service
        return ResponseEntity.ok(service.authenticate(request));
    }
}
