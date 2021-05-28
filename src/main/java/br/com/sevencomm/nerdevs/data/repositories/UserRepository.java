package br.com.sevencomm.nerdevs.data.repositories;

import br.com.sevencomm.nerdevs.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);

    Optional<User> findByEmail(String email);

    User findFirstByLogin(String login);

    User findFirstByEmail(String email);
    
    User findFirstByCpf(String cpf);

}
