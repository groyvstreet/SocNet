package repositories;

import entities.Message;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import static java.lang.System.out;

public class MessageRepository {
    private final Connection _connection;

    public MessageRepository(Connection connection) {
        _connection = connection;
    }

    public void addMessage(Message message) {
        var query = STR."INSERT INTO messages VALUES('\{message.id}', '\{message.dateTime}', '\{message.text}', '\{message.chatId}', '\{message.userId}');";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void updateMessage(Message message) {
        var query = STR."UPDATE messages SET date_time='\{message.dateTime}', text='\{message.text}', chat_id='\{message.chatId}', user_id='\{message.userId}' WHERE id='\{message.id}';";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void removeMessage(Message message) {
        var query = STR."DELETE FROM messages WHERE id='\{message.id}'";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public ArrayList<Message> getMessages() {
        var messages = new ArrayList<Message>();
        var query = "SELECT * FROM messages";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                var message = new Message();
                message.id = UUID.fromString(result.getString("id"));
                message.dateTime = result.getDate("date_time");
                message.text = result.getString("text");
                message.chatId = UUID.fromString(result.getString("chat_id"));
                message.userId = UUID.fromString(result.getString("user_id"));
                messages.add(message);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return messages;
    }

    public Message getMessageById(UUID id) {
        Message message = null;
        var query = STR."SELECT * FROM messages WHERE id='\{id}'";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                message = new Message();
                message.id = UUID.fromString(result.getString("id"));
                message.dateTime = result.getDate("date_time");
                message.text = result.getString("text");
                message.chatId = UUID.fromString(result.getString("chat_id"));
                message.userId = UUID.fromString(result.getString("user_id"));
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return message;
    }

    public ArrayList<Message> getMessagesByChatId(UUID id) {
        var messages = new ArrayList<Message>();
        var query = STR."SELECT * FROM messages WHERE chat_id='\{id}'";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                var message = new Message();
                message.id = UUID.fromString(result.getString("id"));
                message.dateTime = result.getDate("date_time");
                message.text = result.getString("text");
                message.chatId = UUID.fromString(result.getString("chat_id"));
                message.userId = UUID.fromString(result.getString("user_id"));
                messages.add(message);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return messages;
    }
}
