package screens;

import constants.Constants;
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

            for (var i = 0; i < 50; i++) {
                console().printf("\n");
            }

            for (var message : messages) {
                console().printf(Constants.GREEN);
                console().printf("Message:\n");
                console().printf("Id: ");
                console().printf(Constants.WHITE);
                console().printf(STR."\{message.getId()}\n");
                console().printf(Constants.GREEN);
                console().printf("Time: ");
                console().printf(Constants.WHITE);
                console().printf(STR."\{message.getDateTime()}\n");
                console().printf(Constants.GREEN);
                console().printf("Text: ");
                console().printf(Constants.WHITE);
                console().printf(STR."\{message.getText()}\n");
                console().printf(Constants.GREEN);
                console().printf("User id: ");
                console().printf(Constants.WHITE);
                console().printf(STR."\{message.getUserId()}\n");
            }

            console().printf("Select option:\n");
            console().printf("0 - Back\n");
            console().printf("1 - Add message\n");
            console().printf("2 - Edit message\n");
            console().printf("3 - Remove message\n");
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
                    console().printf("Enter id: ");
                    var editedMessageId = console().readLine();
                    console().printf("Enter text: ");
                    var editedMessageText = console().readLine();
                    var editedMessage = messageRepository.getMessageById(UUID.fromString(editedMessageId));
                    editedMessage.setText(editedMessageText);
                    messageRepository.updateMessage(editedMessage);
                    break;
                case '3':
                    console().printf("Enter id: ");
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
