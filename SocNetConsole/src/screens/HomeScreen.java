package screens;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;

import static java.lang.System.in;
import static java.lang.System.console;

public class HomeScreen {
    private HomeScreen() {}

    public static void getHomeScreen(Connection connection) throws IOException, ParseException {
        int option;

        while (true) {
            console().printf("\033[H\033[2J");
            console().flush();
            console().printf("Select option:\n");
            console().printf("0 - Log out\n");
            console().printf("1 - Profile\n");
            console().printf("2 - Posts\n");
            console().printf("3 - Photos\n");
            console().printf("4 - Chats\n");
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
