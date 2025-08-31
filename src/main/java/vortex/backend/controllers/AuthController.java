package vortex.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vortex.backend.dto.LoginRequestDTO;
import vortex.backend.dto.LoginResponseDTO;
import vortex.backend.services.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        // Cria um objeto de autenticação com o email e senha fornecidos
        var usernamePassword = new UsernamePasswordAuthenticationToken(
            loginRequest.getEmail(), 
            loginRequest.getSenha()
        );

        // O Spring Security usa o AuthenticationManager para validar as credenciais
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        // Se a autenticação for bem-sucedida, gera o token
        String token = tokenService.gerarToken(auth);

        // Retorna o token no corpo da resposta
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}