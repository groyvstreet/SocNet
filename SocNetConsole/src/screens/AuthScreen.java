package screens;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;

import static java.lang.System.console;
import static java.lang.System.in;

public class AuthScreen {
    private AuthScreen() {}

    public static void getAuthScreen(Connection connection) throws IOException, ParseException {
        int option;

        while (true) {
            for (var i = 0; i < 50; i++) {
                console().printf("\n");
            }

            console().printf("Select option:\n");
            console().printf("0 - Back\n");
            console().printf("1 - Sign in\n");
            console().printf("2 - Sign up\n");
            option = in.read();

            switch (option) {
                case '0':
                    return;
                case '1':
                    SigninScreen.getSigninScreen(connection);
                    break;
                case '2':
                    SignupScreen.getSignupScreen(connection);
                    break;
                default:
                    break;
            }
        }
    }
}
