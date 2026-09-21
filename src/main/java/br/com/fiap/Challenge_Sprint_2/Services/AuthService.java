package br.com.fiap.Challenge_Sprint_2.Services;

import br.com.fiap.Challenge_Sprint_2.Model.User;
import br.com.fiap.Challenge_Sprint_2.Repositories.UserRepository;
import br.com.fiap.Challenge_Sprint_2.Security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(String email, String senha) {
        // O AuthenticationManager intercepta as credenciais e valida a hash automaticamente
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, senha)
        );

        var user = userRepository.findByEmail(email).orElseThrow();
        return jwtService.generateToken(user);
    }

    public String register(User user) {
        // Intercepta a senha em texto limpo e aplica a encriptação BCrypt antes de guardar
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        userRepository.save(user);
        return jwtService.generateToken(user);
    }
}