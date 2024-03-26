package screens;

import java.io.IOException;
import java.sql.*;
import static java.lang.System.*;

public class AuthScreen {
    private AuthScreen() {}

    public static void getAuthScreen(Connection connection) throws IOException {
        int option;

        while (true) {
            out.print("\033[H\033[2J");
            out.flush();
            out.println("Select option:");
            out.println("0 - Back");
            out.println("1 - Sign in");
            out.println("2 - Sign up");
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
