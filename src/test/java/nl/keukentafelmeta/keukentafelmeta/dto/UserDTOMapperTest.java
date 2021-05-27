package nl.keukentafelmeta.keukentafelmeta.dto;

import nl.keukentafelmeta.keukentafelmeta.domain.User;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOMapperTest {
    private final ModelMapper mapper = new ModelMapper();

    @Test
    void testToEntity() {
        UserDTO userDTO = new UserDTO("Monk", "Colin", "de Bruin", "Bla", "blabla");
        User entity = mapper.map(userDTO, User.class);

        assertNotNull(entity);
        // Added getter to DTO
        assertEquals(entity.getUsername(), userDTO.getUsername());
        // No getter for this in the dto so it's null
        assertNull(entity.getFirstName());
    }
}