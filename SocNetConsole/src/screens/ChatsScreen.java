package screens;

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
            var chatUsers = chatUserRepository.getChatUsersByUserId(IdentityUser.user.id);
            var chats = new ArrayList<Chat>();

            for (var chatUser : chatUsers) {
                var chat = chatRepository.getChatById(chatUser.chatId);
                chats.add(chat);
            }

            out.print("\033[H\033[2J");
            out.flush();

            for (var chat : chats) {
                out.print("\u001B[32m");
                out.println("Chat:");
                out.print("Id: ");
                out.print("\u001B[0m");
                out.println(chat.id);
                out.print("\u001B[32m");
                out.print("Name: ");
                out.print("\u001B[0m");
                out.println(chat.name);
            }

            out.println("Select option:");
            out.println("0 - Back");
            out.println("1 - Add chat");
            out.println("2 - Edit chat");
            out.println("3 - Remove chat");
            out.println("4 - Select chat");
            option = in.read();

            switch (option) {
                case '0':
                    return;
                case '1':
                    out.print("Enter name: ");
                    var addedChatName = console().readLine();
                    var addedChat = new Chat(addedChatName);
                    chatRepository.addChat(addedChat);
                    var chatUser = new ChatUser(addedChat.id, IdentityUser.user.id);
                    chatUserRepository.addChatUser(chatUser);
                    break;
                case '2':
                    out.print("Enter id: ");
                    var editedChatId = console().readLine();
                    out.print("Enter name: ");
                    var editedChatName = console().readLine();
                    var editedChat = chatRepository.getChatById(UUID.fromString(editedChatId));
                    editedChat.name = editedChatName;
                    chatRepository.updateChat(editedChat);
                    break;
                case '3':
                    out.print("Enter id: ");
                    var removedChatId = console().readLine();
                    var removedChat = chatRepository.getChatById(UUID.fromString(removedChatId));
                    chatRepository.removeChat(removedChat);
                    break;
                case '4':
                    out.print("Enter id: ");
                    var selectedChatId = console().readLine();
                    ChatScreen.getChatScreen(connection, UUID.fromString(selectedChatId));
                    break;
                default:
                    break;
            }
        }
    }
}
