package screens;

import java.sql.*;
import static java.lang.System.*;

public class SignupScreen {
    private SignupScreen() {}

    public static void getSignupScreen(Connection connection) {
        out.print("Enter first name: ");
        var firstName = console().readLine();
        out.print("Enter last name: ");
        var lastName = console().readLine();
        out.print("Enter birth date: ");
        var birthDate = console().readLine();

        var id = java.util.UUID.randomUUID();
        var query = STR."INSERT INTO USERS VALUES('\{id}', '\{firstName}', '\{lastName}', '\{birthDate}', '', '00000000-0000-0000-0000-000000000001');";

        try (var statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }
}
