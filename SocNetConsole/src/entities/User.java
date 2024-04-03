package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends Entity {
    private UUID id = UUID.randomUUID();
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String image;
    private UUID roleId;
    private ArrayList<UUID> chatIds = new ArrayList<>();

    public User() {}

    public User(String email,
                String password,
                String firstName,
                String lastName,
                Date birthDate,
                String image,
                UUID roleId) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.image = image;
        this.roleId = roleId;
    }
}
