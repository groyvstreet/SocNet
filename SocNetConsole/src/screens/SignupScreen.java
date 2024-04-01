package screens;

import entities.User;
import identity.IdentityUser;
import repositories.UserRepository;
import utils.UserInput;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class SignupScreen {
    private SignupScreen() {}

    public static void getSignupScreen(Connection connection) throws ParseException, IOException {
        var userRepository = new UserRepository(connection);

        var image = "";
        var roleId = UUID.fromString("00000000-0000-0000-0000-000000000001");

        UserInput.fillUserInput();

        var dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        var user = new User(UserInput.getEmail(), UserInput.getPassword(), UserInput.getFirstName(), UserInput.getLastName(), dateFormatter.parse(UserInput.getBirthDate()), image, roleId);
        userRepository.addUser(user);

        IdentityUser.setUser(user);
        HomeScreen.getHomeScreen(connection);
    }
}
