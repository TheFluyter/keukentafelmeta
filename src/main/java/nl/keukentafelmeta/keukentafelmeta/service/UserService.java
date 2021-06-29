package nl.keukentafelmeta.keukentafelmeta.service;

import nl.keukentafelmeta.keukentafelmeta.domain.User;
import nl.keukentafelmeta.keukentafelmeta.dto.UserDTO;
import nl.keukentafelmeta.keukentafelmeta.persistence.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;


/**
 * This is the KeukenTafelMeta UserService.
 *
 * @author Rick de Ruiter
 * @version %I%
 */
@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper mapper;

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
     * Saves the user to the database if user doesn't already exists.
     *
     * @param userDTO user to save to the database
     * @return UserDTO user that is saved to the database
     */
    public UserDTO saveUser(UserDTO userDTO) {
        User savedUser = userRepository.save(mapToEntity(userDTO));
        return mapToDTO(savedUser);
    }

    public boolean doesUsernameExists(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    public boolean doesEmailExists(String username) {
        User user = userRepository.findByEmail(username);
        return user != null;
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
