package nl.keukentafelmeta.keukentafelmeta.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@Table (name = "users")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "user_id")
    private long id;

    @NotBlank()
    @Size(min = 2)
    @Column(name = "user_name")
    private String username;

    @NotNull()
    @Email
    @Column(nullable = false, name = "user_email")
    private String email;

    @NotBlank()
    @Size(min = 2)
    @Column(nullable = false, name = "user_first_name")
    private String firstName;

    @NotBlank()
    @Size(min = 2)
    @Column(nullable = false, name = "user_last_name")
    private String lastName;

    @NotNull()
    @Size(min = 8)
    @Column(nullable = false, name = "user_password")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "roles",
            joinColumns = @JoinColumn(name = "id")
            )
    @Column(name ="user_role")
    private Set<String> roles;

}
