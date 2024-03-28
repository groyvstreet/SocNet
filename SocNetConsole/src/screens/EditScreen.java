package screens;

import identity.IdentityUser;
import repositories.UserRepository;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.lang.System.console;

public class EditScreen {
    private EditScreen() {}

    public static void getEditScreen(Connection connection) throws ParseException {
        var userRepository = new UserRepository(connection);

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

        IdentityUser.getUser().setEmail(email.isEmpty() ? IdentityUser.getUser().getEmail() : email);
        IdentityUser.getUser().setPassword(password.isEmpty() ? IdentityUser.getUser().getPassword() : password);
        IdentityUser.getUser().setFirstName(firstName.isEmpty() ? IdentityUser.getUser().getFirstName() : firstName);
        IdentityUser.getUser().setLastName(lastName.isEmpty() ? IdentityUser.getUser().getLastName() : lastName);
        IdentityUser.getUser().setBirthDate(birthDate.isEmpty() ? IdentityUser.getUser().getBirthDate() : dateFormatter.parse(birthDate));

        userRepository.updateUser(IdentityUser.getUser());
    }
}
