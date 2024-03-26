import screens.MainScreen;
import java.sql.*;
import static java.lang.System.*;

public class Main {
    public static void main(String[] args) {
        try {
            var connection = DriverManager.getConnection("jdbc:postgresql://localhost/socnet", "postgres", "");

            MainScreen.getMainScreen(connection);
        }
        catch (Exception exception) {
            out.println(exception.getMessage());
        }
    }
}
