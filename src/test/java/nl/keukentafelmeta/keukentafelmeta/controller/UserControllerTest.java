package nl.keukentafelmeta.keukentafelmeta.controller;

import nl.keukentafelmeta.keukentafelmeta.dto.UserDTO;
import nl.keukentafelmeta.keukentafelmeta.repository.UserRepository;
import nl.keukentafelmeta.keukentafelmeta.service.UserService;
import nl.keukentafelmeta.keukentafelmeta.util.JWTUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;

//@SpringBootTest
//@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTUtil jwtUtil;

    private UserController fixture;

    @BeforeEach
    public void setUp() {
        this.fixture = new UserController(userService, userRepository, authenticationManager, jwtUtil);
    }


//    @Test
//    void shouldRegisterUser() throws Exception {
//        // Given
//        UserDTO userDTO = new UserDTO("name", "Colin", "Ruiter", "email@email.nl", "tesdkfhjfdt");
//
//        // When
//        MvcResult result = this.mockMvc.perform(post("/user")
//                .content(asJsonString(userDTO))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        Assertions.assertNotNull(result);
//    }

    @Test
    void shouldRegisterUser() {
        // Given
        UserDTO userDTO = new UserDTO("name", "Colin", "Ruiter", "email@email.nl", "tesdkfhjfdt");
        //When
        ResponseEntity<Object> responseEntity = fixture.registerUser(userDTO);

        // Then
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void shouldNotRegisterUserOnExistingUserName(){
        // Given
        UserDTO userDTO = new UserDTO("name", "Colin", "Ruiter", "email@email.nl", "tesdkfhjfdt");
        Mockito.when(userRepository.existsByUsername(any())).thenReturn(true);
        // When
        ResponseEntity<Object> responseEntity = fixture.registerUser(userDTO);
        // Then
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}