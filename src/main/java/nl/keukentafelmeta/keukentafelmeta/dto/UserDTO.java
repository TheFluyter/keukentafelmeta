package nl.keukentafelmeta.keukentafelmeta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "Please provide a username USERDTO")
    @Size(min = 2, message = "Username should be at least two characters")
    @JsonProperty(value = "username", required = true)
    private String username;

    @NotBlank(message = "Please provide a first name")
    @Size(min = 2, message = "First name should be at least two characters")
    @JsonProperty("firstName")
    private String firstName;

    @NotBlank(message = "Please provide a last name")
    @Size(min = 2, message = "Last name should be at least two characters")
    @JsonProperty("lastName")
    private String lastName;

    @NotNull(message = "Please provide an email")
    @Email
    @JsonProperty("email")
    private String email;

    @NotNull(message = "Please provide a password")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @JsonProperty("password")
    private String password;
}
