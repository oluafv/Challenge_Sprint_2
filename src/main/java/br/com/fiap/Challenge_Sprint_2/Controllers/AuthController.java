package br.com.fiap.Challenge_Sprint_2.Controllers;

import br.com.fiap.Challenge_Sprint_2.DTOs.AuthResponse;
import br.com.fiap.Challenge_Sprint_2.DTOs.LoginRequest;
import br.com.fiap.Challenge_Sprint_2.Model.User;
import br.com.fiap.Challenge_Sprint_2.Services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.email(), request.senha());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User request) {
        String token = authService.register(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}