package vortex.backend.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vortex.backend.dto.UserProfileDTO;
import vortex.backend.dto.UserUpdateDTO;
import vortex.backend.entities.User;
import vortex.backend.repositories.UserRepository;
import org.springframework.security.core.Authentication;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User cadastrarUsuario(User usuario) {
        
        if (userRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalStateException("Este e-mail já está em uso.");
        }
        
        if (userRepository.findByCpf(usuario.getCpf()).isPresent()) {
            throw new IllegalStateException("Este CPF já está cadastrado.");
        }

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        return userRepository.save(usuario);
    }

    public UserProfileDTO getLoggedUserProfile() {
        // O Spring Security armazena os detalhes do usuário autenticado no SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new IllegalStateException("Nenhum usuário autenticado encontrado.");
        }

        // O "principal" é o objeto User que definimos ao implementar UserDetails
        User loggedUser = (User) authentication.getPrincipal();

        // Converte a entidade User para o DTO de perfil antes de retornar
        return new UserProfileDTO(loggedUser);
    }
    
    public UserProfileDTO updateUserProfile(UserUpdateDTO updateData) {
        // Pega o usuário logado da mesma forma que fizemos antes
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Atualiza os campos se eles não forem nulos no DTO
        if (updateData.getNome() != null) {
            loggedUser.setNome(updateData.getNome());
        }
        if (updateData.getTelefone() != null) {
            loggedUser.setTelefone(updateData.getTelefone());
        }

        // Salva o usuário atualizado no banco de dados
        User updatedUser = userRepository.save(loggedUser);

        // Retorna o perfil atualizado
        return new UserProfileDTO(updatedUser);
    }
}