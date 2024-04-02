package screens;

import constants.Constants;
import entities.Chat;
import identity.IdentityUser;
import repositories.ChatRepository;
import repositories.UserRepository;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.UUID;

import static java.lang.System.*;

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

            for (var i = 0; i < 50; i++) {
                console().printf("\n");
            }

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
                console().printf(Constants.GREEN);
                console().printf("Users:\n");
                console().printf(Constants.WHITE);
                chat.getUserIds().forEach(userId -> console().printf(STR."\t\{userId}\n"));
                console().printf(Constants.GREEN);
                console().printf("Messages:\n");
                console().printf(Constants.WHITE);
                chat.getMessageIds().forEach(messageId -> console().printf(STR."\t\{messageId}\n"));
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
