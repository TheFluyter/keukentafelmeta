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
     * Converts UserDTO to User object and saves the user to the database.
     *
     * @param userDTO user to save to the database
     */
    public void saveUser(UserDTO userDTO) {
        System.out.println("User before mapping: " + userDTO);
        User user = mapToEntity(userDTO);
        System.out.println("User after mapping: " + user);
        userRepository.save(user);
    }

    /**
     * Converts DTO to entity.
     *
     * @param user User that needs conversion to DTO
     * @return UserDTO
     */
    private UserDTO mapToDTO(User user) {
        return mapper.map(user, UserDTO.class);
    }

    /**
     * Converts Entity to DTO.
     *
     * @param userDTO UserDTO that needs conversion to Entity
     * @return User
     */
    private User mapToEntity(UserDTO userDTO) {
        return mapper.map(userDTO, User.class);
    }

    private List<UserDTO> mapToDTOList(List<User> users) {
        Type listType = new TypeToken<List<UserDTO>>(){}.getType();
        return mapper.map(users, listType);
    }
}
