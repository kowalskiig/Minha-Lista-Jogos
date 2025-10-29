package project.games.personal.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.games.personal.dto.security.AccountCredentialsDTO;
import project.games.personal.dto.security.TokenDTO;
import project.games.personal.security.jwt.JwtTokenProvider;

import java.time.Instant;

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwtProvider;

    public AuthService(AuthenticationManager authManager, JwtTokenProvider jwtProvider) {
        this.authManager = authManager;
        this.jwtProvider = jwtProvider;
    }

    @Transactional(readOnly = true)
    public TokenDTO login(AccountCredentialsDTO dto) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getEmail(),
                dto.getPassword()));

        String token = jwtProvider.createToken(dto.getEmail(), auth.getAuthorities());
        Instant expires = Instant.now().plusMillis(jwtProvider.getValidityMs());

        return new TokenDTO(dto.getEmail(), token, expires);
    }



}
