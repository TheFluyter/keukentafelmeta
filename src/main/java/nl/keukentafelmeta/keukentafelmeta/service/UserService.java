package nl.keukentafelmeta.keukentafelmeta.service;

import nl.keukentafelmeta.keukentafelmeta.entity.User;
import nl.keukentafelmeta.keukentafelmeta.dto.UserDTO;
import nl.keukentafelmeta.keukentafelmeta.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


/**
 * This is the KeukenTafelMeta UserService.
 *
 * @author Rick de Ruiter
 * @version %I%
 */
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private ModelMapper mapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserService(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    /**
     * Finds all users available in the database and return a list of UserDTO.
     *
     * @return List<UserDTO> all users available in database
     */
    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return mapToDTOList(users);
    }

    /**
     * Saves the user to the database after encrypting their password.
     *
     * @param userDTO user to save to the database
     * @return UserDTO user that is saved to the database
     */
    public UserDTO saveUser(UserDTO userDTO) {
        User user = mapToEntity(userDTO);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return mapToDTO(userRepository.save(user));
    }

    /**
     * Returns User if user is present by the given username.
     *
     * @param username username to find user
     * @return Optional<User>
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Returns User if user is present by given email.
     *
     * @param email email to find user
     * @return Optional<User>
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Checks if the given username is already present in the database.
     *
     * @param username username to find
     * @return boolean
     */
    public boolean doesUsernameExists(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent();
    }

    /**
     * Checks if the given email is already present in the database.
     *
     * @param email
     * @return boolean
     */
    public boolean doesEmailExists(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    /**
     * TODO
     *
     * @param username user to retrieve by username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opt = userRepository.findByUsername(username);
        org.springframework.security.core.userdetails.User springUser = null;

        if(opt.isEmpty()) {
            throw new UsernameNotFoundException("User with username " + username + " not found.");
        } else {
            User user = opt.get(); // retrieving user from DB
            Set<String> roles = user.getRoles();
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for(String role : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role));
            }

            springUser = new org.springframework.security.core.userdetails.User(
                    username,
                    user.getPassword(),
                    grantedAuthorities);

        }
        return springUser;
    }

    /**
     * Converts Entity to DTO.
     *
     * @param user User that needs conversion to DTO
     * @return UserDTO
     */
    private UserDTO mapToDTO(User user) {
        return mapper.map(user, UserDTO.class);
    }

    /**
     * Converts DTO to Entity.
     *
     * @param userDTO UserDTO that needs conversion to Entity
     * @return User
     */
    private User mapToEntity(UserDTO userDTO) {
        return mapper.map(userDTO, User.class);
    }

    /**
     * Converts a list of Entities to a list of DTO's.
     *
     * @param users users that need conversion.
     * @return UserDTO
     */
    private List<UserDTO> mapToDTOList(List<User> users) {
        Type listType = new TypeToken<List<UserDTO>>(){}.getType();
        return mapper.map(users, listType);
    }
}
