package nl.keukentafelmeta.keukentafelmeta.controller;

import nl.keukentafelmeta.keukentafelmeta.dto.UserDTO;
import nl.keukentafelmeta.keukentafelmeta.entity.UserRequest;
import nl.keukentafelmeta.keukentafelmeta.entity.UserResponse;
import nl.keukentafelmeta.keukentafelmeta.service.UserService;
import nl.keukentafelmeta.keukentafelmeta.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private JWTUtil util;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers the user to the database. Checks if username and email are not already taken,
     * if so it returns a 409 status code. Otherwise it returns a 201 with an user object.
     *
     * @param newUser user to register
     * @return Status http status code
     */
    @PostMapping("/user")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserDTO newUser) {
        // TODO Replace the doesUserNameExists and doesEmailExists by @Column(unique = "true")
        if (userService.doesUsernameExists(newUser.getUsername())) {

            log.info("Failed to save user \"{}\" to the database, because username is already taken", newUser.getUsername());
            return new ResponseEntity<>("User with username \"" + newUser.getUsername() + "\" already exists",
                    HttpStatus.CONFLICT);
        } else if (userService.doesEmailExists(newUser.getEmail())) {
            log.info("Failed to save user \"{}\" to the database, because provided email is already taken", newUser.getUsername());
            return new ResponseEntity<>("Email \"" + newUser.getEmail() + "\" is already taken, please provide " +
                    "a different email", HttpStatus.CONFLICT);
        } else {
            UserDTO savedUser = userService.saveUser(newUser);
            log.info("User saved to the database: \"{}\"", newUser.getUsername());
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
    }

    @GetMapping("/user/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody UserRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()));
        String token = util.generateToken(request.getUsername());
        return ResponseEntity.ok(new UserResponse(token, "Token generated successfully!"));

    }
}
