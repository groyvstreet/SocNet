package screens;

import entities.Message;
import identity.IdentityUser;
import repositories.MessageRepository;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.UUID;

import static java.lang.System.*;

public class ChatScreen {
    private ChatScreen() {}

    public static void getChatScreen(Connection connection, UUID chatId) throws IOException {
        var messageRepository = new MessageRepository(connection);

        int option;

        while (true) {
            var messages = messageRepository.getMessagesByChatId(chatId);

            out.print("\033[H\033[2J");
            out.flush();

            for (var message : messages) {
                out.print("\u001B[32m");
                out.println("Message:");
                out.print("Id: ");
                out.print("\u001B[0m");
                out.println(message.id);
                out.print("\u001B[32m");
                out.print("Time: ");
                out.print("\u001B[0m");
                out.println(message.dateTime);
                out.print("\u001B[32m");
                out.print("Text: ");
                out.print("\u001B[0m");
                out.println(message.text);
                out.print("\u001B[32m");
                out.print("User id: ");
                out.print("\u001B[0m");
                out.println(message.userId);
            }

            out.println("Select option:");
            out.println("0 - Back");
            out.println("1 - Add message");
            out.println("2 - Edit message");
            out.println("3 - Remove message");
            option = in.read();

            switch (option) {
                case '0':
                    return;
                case '1':
                    out.print("Enter text: ");
                    var addedMessageText = console().readLine();
                    var addedMessage = new Message(new Date(), addedMessageText, chatId, IdentityUser.user.id);
                    messageRepository.addMessage(addedMessage);
                    break;
                case '2':
                    out.print("Enter id: ");
                    var editedMessageId = console().readLine();
                    out.print("Enter text: ");
                    var editedMessageText = console().readLine();
                    var editedMessage = messageRepository.getMessageById(UUID.fromString(editedMessageId));
                    editedMessage.text = editedMessageText;
                    out.println("GOOD");
                    messageRepository.updateMessage(editedMessage);
                    break;
                case '3':
                    out.print("Enter id: ");
                    var removedMessageId = console().readLine();
                    var removedMessage = messageRepository.getMessageById(UUID.fromString(removedMessageId));
                    messageRepository.removeMessage(removedMessage);
                    break;
                default:
                    break;
            }
        }
    }
}
