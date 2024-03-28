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
            console().printf("\033[H\033[2J");
            console().flush();
            console().printf("Select option:\n");
            console().printf("0 - Exit\n");
            console().printf("1 - Authorize\n");
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
