package screens;

import entities.User;
import identity.IdentityUser;
import repositories.UserRepository;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;

import static java.lang.System.*;

public class SigninScreen {
    private SigninScreen() {}

    public static void getSigninScreen(Connection connection) throws IOException, ParseException {
        var userRepository = new UserRepository(connection);

        User user;

        do {
            console().printf("Enter email: ");
            var email = console().readLine();
            console().printf("Enter password: ");
            var password = console().readLine();

            user = userRepository.getUserByEmailAndPassword(email, password);
        } while (user == null);

        IdentityUser.setUser(user);
        HomeScreen.getHomeScreen(connection);
    }
}
