package com.example.forohub.controller;

import com.example.forohub.dto.AuthRequest;
import com.example.forohub.dto.AuthResponse;
import com.example.forohub.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid AuthRequest req) {
        authService.register(req.username(), req.password());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest req) {
        String token = authService.login(req.username(), req.password());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
