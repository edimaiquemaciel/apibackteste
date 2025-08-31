package vortex.backend.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import vortex.backend.config.JwtProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import vortex.backend.entities.User;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenService {

    // As propriedades agora são 'final' porque são definidas no construtor e não mudam mais
    private final String jwtSecret;
    private final long jwtExpiration;

    // Construtor é o único responsável por injetar as propriedades
    public TokenService(JwtProperties jwtProperties) {
        this.jwtSecret = jwtProperties.getSecret();
        this.jwtExpiration = jwtProperties.getExpiration();
    }

    /**
     * Gera um token JWT para o usuário autenticado.
     * @param authentication O objeto de autenticação do Spring Security.
     * @return Uma string representando o token JWT.
     */
    public String gerarToken(Authentication authentication) {
        User usuarioLogado = (User) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + jwtExpiration);

        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .issuer("API Vortex Backend")
                .subject(usuarioLogado.getEmail())
                .issuedAt(hoje)
                .expiration(dataExpiracao)
                .signWith(secretKey)
                .compact();
    }

    /**
     * Valida um token JWT e extrai o email do usuário.
     * @param token A string do token JWT.
     * @return O email do usuário (subject do token).
     */
    public String getEmailFromToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }
}