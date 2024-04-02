package screens;

import identity.IdentityUser;
import repositories.UserRepository;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;

import static java.lang.System.*;

public class ProfileScreen {
    private ProfileScreen() {}

    public static void getProfileScreen(Connection connection) throws IOException, ParseException {
        var userRepository = new UserRepository(connection);

        int option;

        while (true) {
            for (var i = 0; i < 50; i++) {
                console().printf("\n");
            }

            console().printf(STR."First name: \{IdentityUser.getUser().getFirstName()}\n");
            console().printf(STR."Last name: \{IdentityUser.getUser().getLastName()}\n");
            console().printf(STR."Birth date: \{IdentityUser.getUser().getBirthDate()}\n");
            console().printf(STR."Email: \{IdentityUser.getUser().getEmail()}\n");
            console().printf(STR."Image: \{IdentityUser.getUser().getImage()}\n");
            console().printf("Chats:\n");
            IdentityUser.getUser().getChatIds().forEach(chatId -> console().printf(STR."\t\{chatId}\n"));
            console().printf("Select option:\n");
            console().printf("0 - Back\n");
            console().printf("1 - Edit\n");
            console().printf("2 - Remove account\n");
            option = in.read();

            switch (option) {
                case '0':
                    return;
                case '1':
                    EditScreen.getEditScreen(connection);
                    return;
                case '2':
                    userRepository.removeUser(IdentityUser.getUser());
                    return;
                default:
                    break;
            }
        }
    }
}
