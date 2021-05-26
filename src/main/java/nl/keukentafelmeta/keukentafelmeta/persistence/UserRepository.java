package nl.keukentafelmeta.keukentafelmeta.persistence;

import nl.keukentafelmeta.keukentafelmeta.domain.User;
import nl.keukentafelmeta.keukentafelmeta.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//TODO Document your public API
// Maybe we will use < USER, String> instead of Long for looking up stuff but we will see
public interface UserRepository extends JpaRepository<User, Long> {
}
