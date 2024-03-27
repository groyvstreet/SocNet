package screens;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;

import static java.lang.System.*;

public class MainScreen {
    private MainScreen() {}

    public static void getMainScreen(Connection connection) throws IOException, ParseException {
        int option;

        while (true) {
            out.print("\033[H\033[2J");
            out.flush();
            out.println("Select option:");
            out.println("0 - Exit");
            out.println("1 - Authorize");
            option = in.read();

            switch (option) {
                case '0':
                    return;
                case '1':
                    AuthScreen.getAuthScreen(connection);
                    break;
                default:
                    break;
            }
        }
    }
}
