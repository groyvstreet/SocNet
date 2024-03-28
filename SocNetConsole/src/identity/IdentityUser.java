package identity;

import entities.User;
import lombok.Getter;
import lombok.Setter;

public class IdentityUser {
    private IdentityUser() {}

    @Getter
    @Setter
    private static User user;

}
