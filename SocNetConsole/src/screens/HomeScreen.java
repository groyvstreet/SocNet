package screens;

import repositories.PostRepository;
import repositories.UserRepository;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;

import static java.lang.System.in;
import static java.lang.System.out;

public class HomeScreen {
    private HomeScreen() {}

    public static void getHomeScreen(Connection connection) throws IOException, ParseException {
        int option;

        while (true) {
            out.print("\033[H\033[2J");
            out.flush();
            out.println("Select option:");
            out.println("0 - Log out");
            out.println("1 - Profile");
            out.println("2 - Posts");
            out.println("3 - Photos");
            out.println("4 - Chats");
            option = in.read();

            switch (option) {
                case '0':
                    return;
                case '1':
                    ProfileScreen.getProfileScreen(connection);
                    break;
                case '2':
                    PostsScreen.getPostsScreen(connection);
                    break;
                case '3':
                    PhotosScreen.getPhotosScreen(connection);
                    break;
                case '4':
                    ChatsScreen.getChatsScreen(connection);
                    break;
                default:
                    break;
            }
        }
    }
}
