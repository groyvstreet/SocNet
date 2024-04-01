package screens;

import identity.IdentityUser;
import repositories.UserRepository;
import utils.UserInput;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EditScreen {
    private EditScreen() {}

    public static void getEditScreen(Connection connection) throws ParseException {
        var userRepository = new UserRepository(connection);

        UserInput.fillUserInput();

        var dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

        IdentityUser.getUser().setEmail(UserInput.getEmail().isEmpty() ? IdentityUser.getUser().getEmail() : UserInput.getEmail());
        IdentityUser.getUser().setPassword(UserInput.getPassword().isEmpty() ? IdentityUser.getUser().getPassword() : UserInput.getPassword());
        IdentityUser.getUser().setFirstName(UserInput.getFirstName().isEmpty() ? IdentityUser.getUser().getFirstName() : UserInput.getFirstName());
        IdentityUser.getUser().setLastName(UserInput.getLastName().isEmpty() ? IdentityUser.getUser().getLastName() : UserInput.getLastName());
        IdentityUser.getUser().setBirthDate(UserInput.getBirthDate().isEmpty() ? IdentityUser.getUser().getBirthDate() : dateFormatter.parse(UserInput.getBirthDate()));

        userRepository.updateUser(IdentityUser.getUser());
    }
}
