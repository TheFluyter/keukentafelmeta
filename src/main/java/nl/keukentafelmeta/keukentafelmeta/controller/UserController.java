package nl.keukentafelmeta.keukentafelmeta.controller;

import nl.keukentafelmeta.keukentafelmeta.domain.Status;
import nl.keukentafelmeta.keukentafelmeta.domain.User;
import nl.keukentafelmeta.keukentafelmeta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// TODO Document your public API's and methods
// https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html
@RestController
public class UserController {

    // TODO https://blog.marcnuri.com/field-injection-is-not-recommended/
    @Autowired
    UserRepository userRepository;

    @PostMapping("/user")
    //TODO normally a "persistent" (newUser) object is ot used in the interface, don't think it's a problem for now.
    // Other solutions could be using a POJO or DTO
    public Status registerUser(@Valid @RequestBody User newUser) {
        List<User> users = userRepository.findAll();
// TODO Look into the JPARepository methods (I've to do it myself as well) I think there is a method userRepository.exists()
        for (User user : users) {
            if (user.equals(newUser)) {
                //TODO use a LOGGER instead of System.out.println  (slf4j)
                System.out.println("User already exists!");
                return Status.USER_ALREADY_EXISTS;
            }
        }

        userRepository.save(newUser);
        // TODO I think we need, or can, return a response to the front end in the form of a HttpStatus / ResponsEntity
        // If we do this the method will be something like public ResponseEntity<> registerUser
        // https://www.baeldung.com/spring-response-entity
        return Status.SUCCESS;
    }

    @GetMapping("/user/login")
    // TODO We have to revise this section return a JWT token etc
    public Status loginUser(@Valid @RequestBody User user) {
        List<User> users = userRepository.findAll();

        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(true);
                userRepository.save(user);
                return Status.SUCCESS;
            }
        }

        return Status.FAILURE;
    }

    // TODO We should write Junit tests, but I can look into this
}
