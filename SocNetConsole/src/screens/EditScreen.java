package screens;

import entities.User;
import identity.IdentityUser;
import repositories.UserRepository;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.lang.System.console;
import static java.lang.System.out;

public class EditScreen {
    private EditScreen() {}

    public static void getEditScreen(Connection connection) throws ParseException {
        var userRepository = new UserRepository(connection);

        out.print("Enter first name: ");
        var firstName = console().readLine();
        out.print("Enter last name: ");
        var lastName = console().readLine();
        out.print("Enter birth date: ");
        var birthDate = console().readLine();
        out.print("Enter email: ");
        var email = console().readLine();
        out.print("Enter password: ");
        var password = console().readLine();

        var dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

        IdentityUser.user.email = email.isEmpty() ? IdentityUser.user.email : email;
        IdentityUser.user.password = password.isEmpty() ? IdentityUser.user.password : password;
        IdentityUser.user.firstName = firstName.isEmpty() ? IdentityUser.user.firstName : firstName;
        IdentityUser.user.lastName = lastName.isEmpty() ? IdentityUser.user.lastName : lastName;
        IdentityUser.user.birthDate = birthDate.isEmpty() ? IdentityUser.user.birthDate : dateFormatter.parse(birthDate);

        userRepository.updateUser(IdentityUser.user);
    }
}
