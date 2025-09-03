package vortex.backend.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vortex.backend.dto.UserProfileDTO;
import vortex.backend.dto.UserUpdateDTO;
import vortex.backend.entities.User;
import vortex.backend.repositories.UserRepository;

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

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return userRepository.save(usuario);
    }

    public UserProfileDTO getLoggedUserProfile() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User loggedUser) {
            return new UserProfileDTO(loggedUser);
        }

        throw new IllegalStateException("Nenhum usuário autenticado encontrado.");
    }

    public UserProfileDTO updateUserProfile(UserUpdateDTO updateData) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof User loggedUser)) {
            throw new IllegalStateException("Nenhum usuário autenticado encontrado.");
        }

        if (updateData.getNome() != null) {
            loggedUser.setNome(updateData.getNome());
        }
        if (updateData.getTelefone() != null) {
            loggedUser.setTelefone(updateData.getTelefone());
        }

        User updatedUser = userRepository.save(loggedUser);
        return new UserProfileDTO(updatedUser);
    }
}
