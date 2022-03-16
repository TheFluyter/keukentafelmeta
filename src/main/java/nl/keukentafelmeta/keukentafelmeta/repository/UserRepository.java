package nl.keukentafelmeta.keukentafelmeta.repository;

import nl.keukentafelmeta.keukentafelmeta.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//TODO Document your public API
// Maybe we will use < USER, String> instead of Long for looking up stuff but we will see
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
