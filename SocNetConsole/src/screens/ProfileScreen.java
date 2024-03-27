package screens;

import identity.IdentityUser;
import repositories.UserRepository;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;

import static java.lang.System.*;

public class ProfileScreen {
    private ProfileScreen() {}

    public static void getProfileScreen(Connection connection) throws IOException, ParseException {
        var userRepository = new UserRepository(connection);

        int option;

        while (true) {
            out.print("\033[H\033[2J");
            out.flush();
            out.println(STR."First name: \{IdentityUser.user.firstName}");
            out.println(STR."Last name: \{IdentityUser.user.lastName}");
            out.println(STR."Birth date: \{IdentityUser.user.birthDate}");
            out.println(STR."Email: \{IdentityUser.user.email}");
            out.println(STR."Image: \{IdentityUser.user.image}");
            out.println("Select option:");
            out.println("0 - Back");
            out.println("1 - Edit");
            out.println("2 - Remove account");
            option = in.read();

            switch (option) {
                case '0':
                    return;
                case '1':
                    EditScreen.getEditScreen(connection);
                    return;
                case '2':
                    userRepository.removeUser(IdentityUser.user);
                    return;
                default:
                    break;
            }
        }
    }
}
