package vortex.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // Cria um construtor com todos os argumentos
public class LoginResponseDTO {
    private String token;
}