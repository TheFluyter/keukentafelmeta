package nl.keukentafelmeta.keukentafelmeta.controller;

import nl.keukentafelmeta.keukentafelmeta.dto.UserDTO;
import nl.keukentafelmeta.keukentafelmeta.entity.UserRequest;
import nl.keukentafelmeta.keukentafelmeta.entity.UserResponse;
import nl.keukentafelmeta.keukentafelmeta.repository.UserRepository;
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

/**
 * This the KeukenTafelMeta RestController.
 *
 * @author Rick de Ruiter
 * @version %I%
 */

@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil util;

    @Autowired
    UserController(UserService userService, UserRepository userRepository, AuthenticationManager authenticationManager, JWTUtil util) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.util = util;
    }

    /**
     * Registers the user to the database. Checks if username and email are not already taken,
     * if so it returns a 400 status code. Otherwise, it returns a 201 with a user object.
     *
     * @param newUser user to register
     * @return Status http status code
     */

    @PostMapping("/user")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserDTO newUser) {

        if (userRepository.existsByUsername(newUser.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(newUser.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        UserDTO savedUser = userService.saveUser(newUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }

    @GetMapping("/user/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody UserRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()));
        String token = util.generateToken(request.getUsername());
        return ResponseEntity.ok(new UserResponse(token, "Token generated successfully!"));

    }
}
