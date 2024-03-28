package screens;

import constants.Constants;
import entities.Chat;
import entities.ChatUser;
import identity.IdentityUser;
import repositories.ChatRepository;
import repositories.ChatUserRepository;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.UUID;

import static java.lang.System.*;

public class ChatsScreen {
    private ChatsScreen() {}

    public static void getChatsScreen(Connection connection) throws IOException {
        var chatUserRepository = new ChatUserRepository(connection);
        var chatRepository = new ChatRepository(connection);

        int option;

        while (true) {
            var chatUsers = chatUserRepository.getChatUsersByUserId(IdentityUser.getUser().getId());
            var chats = new ArrayList<Chat>();

            for (var chatUser : chatUsers) {
                var chat = chatRepository.getChatById(chatUser.getChatId());
                chats.add(chat);
            }

            console().printf("\033[H\033[2J");
            console().flush();

            for (var chat : chats) {
                console().printf(Constants.GREEN);
                console().printf("Chat:\n");
                console().printf("Id: ");
                console().printf(Constants.WHITE);
                console().printf(STR."\{chat.getId()}\n");
                console().printf(Constants.GREEN);
                console().printf("Name: ");
                console().printf(Constants.WHITE);
                console().printf(STR."\{chat.getName()}\n");
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
                    var chatUser = new ChatUser(addedChat.getId(), IdentityUser.getUser().getId());
                    chatUserRepository.addChatUser(chatUser);
                    break;
                case '2':
                    console().printf("Enter id: ");
                    var editedChatId = console().readLine();
                    console().printf("Enter name: ");
                    var editedChatName = console().readLine();
                    var editedChat = chatRepository.getChatById(UUID.fromString(editedChatId));
                    editedChat.setName(editedChatName);
                    chatRepository.updateChat(editedChat);
                    break;
                case '3':
                    console().printf("Enter id: ");
                    var removedChatId = console().readLine();
                    var removedChat = chatRepository.getChatById(UUID.fromString(removedChatId));
                    chatRepository.removeChat(removedChat);
                    break;
                case '4':
                    console().printf("Enter id: ");
                    var selectedChatId = console().readLine();
                    ChatScreen.getChatScreen(connection, UUID.fromString(selectedChatId));
                    break;
                default:
                    break;
            }
        }
    }
}
