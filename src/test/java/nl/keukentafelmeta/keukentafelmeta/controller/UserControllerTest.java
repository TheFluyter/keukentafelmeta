package nl.keukentafelmeta.keukentafelmeta.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.keukentafelmeta.keukentafelmeta.dto.UserDTO;
import nl.keukentafelmeta.keukentafelmeta.exception.RestExceptionHandler;
import nl.keukentafelmeta.keukentafelmeta.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mvc;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    private JacksonTester<UserDTO> jsonUserDTO;

    final UserDTO request = new UserDTO(
            "TestUser",
            "John",
            "Doe",
            "mock@email.com",
            "password");

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    void registerUserTest() {
    }

    @Test
    void loginUserTest() {
    }
}