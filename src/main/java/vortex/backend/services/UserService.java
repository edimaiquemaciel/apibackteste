package vortex.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vortex.backend.entities.User;
import vortex.backend.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    
    @Autowired
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
}