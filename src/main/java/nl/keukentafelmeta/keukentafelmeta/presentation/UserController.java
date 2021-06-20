package nl.keukentafelmeta.keukentafelmeta.presentation;

import nl.keukentafelmeta.keukentafelmeta.domain.Status;
import nl.keukentafelmeta.keukentafelmeta.dto.UserDTO;
import nl.keukentafelmeta.keukentafelmeta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This the KeukenTafelMeta RestController.
 *
 * @author Rick de Ruiter
 * @version %I%
 */
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers the user to the database.
     *
     * @param newUser user to register
     * @return Status status code
     */
    @PostMapping("/user")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO newUser) {
        List<UserDTO> users = userService.getUsers();

        // TODO Look into the JPARepository methods (I've to do it myself as well)
        //  I think there is a method userRepository.exists()
        for (UserDTO user : users) {
            if (user.equals(newUser)) {
                //TODO use a LOGGER instead of System.out.println  (slf4j)
                return new ResponseEntity<>(newUser, HttpStatus.BAD_REQUEST);
            }
        }

        UserDTO savedUser = userService.saveUser(newUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/user/login")
    // TODO We have to revise this section return a JWT token etc
    public Status loginUser(@Valid @RequestBody UserDTO userDTO) {
        List<UserDTO> users = userService.getUsers();

        for (UserDTO other : users) {
            if (other.equals(userDTO)) {
//              userDTO.setLoggedIn(true);
                return Status.SUCCESS;
            }
        }

        return Status.FAILURE;
    }

    // TODO We should write Junit tests, but I can look into this
}
