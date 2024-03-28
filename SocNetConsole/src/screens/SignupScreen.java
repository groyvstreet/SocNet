package screens;

import entities.User;
import identity.IdentityUser;
import repositories.UserRepository;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import static java.lang.System.*;

public class SignupScreen {
    private SignupScreen() {}

    public static void getSignupScreen(Connection connection) throws ParseException, IOException {
        var userRepository = new UserRepository(connection);

        var image = "";
        var roleId = UUID.fromString("00000000-0000-0000-0000-000000000001");

        console().printf("Enter first name: ");
        var firstName = console().readLine();
        console().printf("Enter last name: ");
        var lastName = console().readLine();
        console().printf("Enter birth date: ");
        var birthDate = console().readLine();
        console().printf("Enter email: ");
        var email = console().readLine();
        console().printf("Enter password: ");
        var password = console().readLine();

        var dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        var user = new User(email, password, firstName, lastName, dateFormatter.parse(birthDate), image, roleId);
        userRepository.addUser(user);

        IdentityUser.setUser(user);
        HomeScreen.getHomeScreen(connection);
    }
}
