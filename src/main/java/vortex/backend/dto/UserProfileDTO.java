package vortex.backend.dto;

import lombok.Getter;
import lombok.Setter;
import vortex.backend.entities.User;

@Getter
@Setter
public class UserProfileDTO {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    // Construtor que converte uma entidade User para o DTO
    public UserProfileDTO(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.telefone = user.getTelefone();
    }
}