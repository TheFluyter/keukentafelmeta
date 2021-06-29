package nl.keukentafelmeta.keukentafelmeta.presentation;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.*;

class ApiErrorTest {

    @Test
    void testSetMessage() throws Throwable {
        // Given
        ApiError underTest = new ApiError(HttpStatus.BAD_REQUEST);

        // When
        underTest.setMessage("This is my message");
        String result = underTest.getMessage();

        // Then
        String expected = "This is my message";
        assertEquals(expected, result);
    }
}