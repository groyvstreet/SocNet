package entities;

import java.util.Date;
import java.util.UUID;

public class User {
    public UUID id = UUID.randomUUID();
    public String email;
    public String password;
    public String firstName;
    public String lastName;
    public Date birthDate;
    public String image;
    public UUID roleId;

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
