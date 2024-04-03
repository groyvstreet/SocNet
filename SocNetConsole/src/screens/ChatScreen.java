package screens;

import entities.Message;
import entities.User;
import identity.IdentityUser;
import repositories.ChatRepository;
import repositories.MessageRepository;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.UUID;

import static java.lang.System.console;
import static java.lang.System.in;

public class ChatScreen {
    private ChatScreen() {}

    public static void getChatScreen(Connection connection, UUID chatId) throws IOException {
        var messageRepository = new MessageRepository(connection);
        var chatRepository = new ChatRepository(connection);

        var chat = chatRepository.getChatById(chatId);

        int option;

        while (true) {
            var messages = messageRepository.getMessagesByChatId(chatId);

            console().printf("\n".repeat(50));

            for (var message : messages) {
                message.print();
            }

            console().printf("Select option:\n");
            console().printf("0 - Back\n");
            console().printf("1 - Add message\n");
            console().printf("2 - Edit message\n");
            console().printf("3 - Remove message\n");
            console().printf("4 - Invite user\n");
            console().printf("5 - Kick user\n");
            option = in.read();

            switch (option) {
                case '0':
                    return;
                case '1':
                    console().printf("Enter text: ");
                    var addedMessageText = console().readLine();
                    var addedMessage = new Message(new Date(), addedMessageText, chatId, IdentityUser.getUser().getId());
                    messageRepository.addMessage(addedMessage);
                    break;
                case '2':
                    console().printf("Enter message id: ");
                    var editedMessageId = console().readLine();
                    console().printf("Enter text: ");
                    var editedMessageText = console().readLine();
                    var editedMessage = messageRepository.getMessageById(UUID.fromString(editedMessageId));
                    editedMessage.setText(editedMessageText);
                    messageRepository.updateMessage(editedMessage);
                    break;
                case '3':
                    console().printf("Enter message id: ");
                    var removedMessageId = console().readLine();
                    var removedMessage = messageRepository.getMessageById(UUID.fromString(removedMessageId));
                    messageRepository.removeMessage(removedMessage);
                    break;
                case '4':
                    console().printf("Enter user id: ");
                    var invitedUserId = UUID.fromString(console().readLine());
                    var invitedUser = new User();
                    invitedUser.setId(invitedUserId);
                    chatRepository.addUserToChat(chat, invitedUser);
                    break;
                case '5':
                    console().printf("Enter user id: ");
                    var kickedUserId = UUID.fromString(console().readLine());
                    var kickedUser = new User();
                    kickedUser.setId(kickedUserId);
                    chatRepository.removeUserFromChat(chat, kickedUser);
                    break;
                default:
                    break;
            }
        }
    }
}
