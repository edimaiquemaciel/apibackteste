package vortex.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vortex.backend.entities.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
 
    Optional<User> findByEmail(String email);

    Optional<User> findByCpf(String cpf);
}