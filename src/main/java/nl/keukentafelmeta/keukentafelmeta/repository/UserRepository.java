package nl.keukentafelmeta.keukentafelmeta.repository;

import nl.keukentafelmeta.keukentafelmeta.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//TODO Document your public API
// Maybe we will use < USER, String> instead of Long foor looking up stuff but we will see
public interface UserRepository extends JpaRepository<User, Long> {
}
