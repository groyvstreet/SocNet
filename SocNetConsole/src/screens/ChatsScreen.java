package screens;

import entities.Chat;
import identity.IdentityUser;
import repositories.ChatRepository;
import repositories.UserRepository;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.UUID;

import static java.lang.System.console;
import static java.lang.System.in;

public class ChatsScreen {
    private ChatsScreen() {}

    public static void getChatsScreen(Connection connection) throws IOException {
        var chatRepository = new ChatRepository(connection);
        var userRepository = new UserRepository(connection);

        int option;

        while (true) {
            IdentityUser.setUser(userRepository.getUserById(IdentityUser.getUser().getId()));
            var chats = new ArrayList<Chat>();

            for (var chatId : IdentityUser.getUser().getChatIds()) {
                var chat = chatRepository.getChatById(chatId);
                chats.add(chat);
            }

            console().printf("\n".repeat(50));

            for (var chat : chats) {
                chat.print();
            }

            console().printf("Select option:\n");
            console().printf("0 - Back\n");
            console().printf("1 - Add chat\n");
            console().printf("2 - Edit chat\n");
            console().printf("3 - Remove chat\n");
            console().printf("4 - Select chat\n");
            option = in.read();

            switch (option) {
                case '0':
                    return;
                case '1':
                    console().printf("Enter name: ");
                    var addedChatName = console().readLine();
                    var addedChat = new Chat(addedChatName);
                    chatRepository.addChat(addedChat);
                    chatRepository.addUserToChat(addedChat, IdentityUser.getUser());
                    break;
                case '2':
                    console().printf("Enter chat id for editing: ");
                    var editedChatId = console().readLine();
                    console().printf("Enter name: ");
                    var editedChatName = console().readLine();
                    var editedChat = chatRepository.getChatById(UUID.fromString(editedChatId));
                    editedChat.setName(editedChatName);
                    chatRepository.updateChat(editedChat);
                    break;
                case '3':
                    console().printf("Enter chat id for removing: ");
                    var removedChatId = console().readLine();
                    var removedChat = chatRepository.getChatById(UUID.fromString(removedChatId));
                    chatRepository.removeChat(removedChat);
                    break;
                case '4':
                    console().printf("Enter chat id for selecting: ");
                    var selectedChatId = console().readLine();
                    ChatScreen.getChatScreen(connection, UUID.fromString(selectedChatId));
                    break;
                default:
                    break;
            }
        }
    }
}
