package vortex.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class User implements UserDetails { // Adiciona "implements UserDetails"

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String senha;

    // Métodos obrigatórios da interface UserDetails
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Define as permissões/roles do usuário. Para este projeto simples,
        // podemos definir um papel padrão para todos.
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        // Retorna a senha do usuário. O Spring Security usará isso para comparar.
        return this.senha;
    }

    @Override
    public String getUsername() {
        // Retorna o campo que será usado como "username" para o login.
        // O e-mail é uma escolha comum e segura.
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // A conta não expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // A conta não está bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // As credenciais não expiram
    }

    @Override
    public boolean isEnabled() {
        return true; // A conta está habilitada
    }
}