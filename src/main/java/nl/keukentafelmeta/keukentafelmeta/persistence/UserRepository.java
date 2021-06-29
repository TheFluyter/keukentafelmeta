package nl.keukentafelmeta.keukentafelmeta.persistence;

import nl.keukentafelmeta.keukentafelmeta.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//TODO Document your public API
// Maybe we will use < USER, String> instead of Long for looking up stuff but we will see
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
}
