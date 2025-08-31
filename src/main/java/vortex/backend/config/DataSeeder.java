package vortex.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import vortex.backend.entities.User;
import vortex.backend.repositories.UserRepository;

@Component
@Profile("dev") // Garante que este seeder só rode no perfil de desenvolvimento
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Verifica se já existe um usuário com este e-mail para não criar duplicatas
        if (userRepository.findByEmail("admin@vortex.com").isEmpty()) {
            
            User adminUser = new User();
            adminUser.setNome("Admin User");
            adminUser.setEmail("admin@vortex.com");
            adminUser.setCpf("00000000000"); // CPF fictício
            adminUser.setTelefone("999999999");
            
            // É fundamental criptografar a senha!
            adminUser.setSenha(passwordEncoder.encode("admin123")); 

            userRepository.save(adminUser);

            System.out.println(">>>>>>>>>> Usuário 'admin@vortex.com' criado com sucesso! <<<<<<<<<<");
        } else {
            System.out.println(">>>>>>>>>> Usuário 'admin@vortex.com' já existe. <<<<<<<<<<");
        }
    }
}