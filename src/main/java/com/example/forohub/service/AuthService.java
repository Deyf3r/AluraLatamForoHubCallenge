package com.example.forohub.service;

import com.example.forohub.entity.Usuario;
import com.example.forohub.repository.UsuarioRepository;
import com.example.forohub.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UsuarioRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwt;
    private final AuthenticationManager authManager;

    public AuthService(UsuarioRepository repo, PasswordEncoder encoder, JwtService jwt, AuthenticationManager authManager) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
        this.authManager = authManager;
    }

    public void register(String username, String rawPassword) {
        if (repo.existsByUsername(username)) {
            throw new IllegalArgumentException("Usuario ya existe");
        }
        Usuario u = new Usuario(username, encoder.encode(rawPassword));
        repo.save(u);
    }

    public String login(String username, String rawPassword) {
        var token = new UsernamePasswordAuthenticationToken(username, rawPassword);
        authManager.authenticate(token);
        return jwt.generateToken(username);
    }
}
