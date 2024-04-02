package repositories;

import entities.Message;

import java.sql.Connection;
import java.sql.ResultSet;
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
        var query = STR."INSERT INTO messages VALUES('\{message.getId()}', '\{message.getDateTime()}', '\{message.getText()}', '\{message.getChatId()}', '\{message.getUserId()}');";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void updateMessage(Message message) {
        var query = STR."UPDATE messages SET date_time='\{message.getDateTime()}', text='\{message.getText()}', chat_id='\{message.getChatId()}', user_id='\{message.getUserId()}' WHERE id='\{message.getId()}';";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void removeMessage(Message message) {
        var query = STR."DELETE FROM messages WHERE id='\{message.getId()}'";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public ArrayList<Message> getMessages() {
        var query = "SELECT * FROM messages";

        return getMessagesFromQuery(query);
    }

    public Message getMessageById(UUID id) {
        Message message = null;
        var query = STR."SELECT * FROM messages WHERE id='\{id}'";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                message = new Message();
                setMessageFields(message, result);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return message;
    }

    public ArrayList<Message> getMessagesByChatId(UUID id) {
        var query = STR."SELECT * FROM messages WHERE chat_id='\{id}'";

        return getMessagesFromQuery(query);
    }

    private ArrayList<Message> getMessagesFromQuery(String query) {
        var messages = new ArrayList<Message>();

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                var message = new Message();
                setMessageFields(message, result);
                messages.add(message);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return messages;
    }

    private void setMessageFields(Message message, ResultSet resultSet) throws SQLException {
        message.setId(UUID.fromString(resultSet.getString("id")));
        message.setDateTime(resultSet.getDate("date_time"));
        message.setText(resultSet.getString("text"));
        message.setChatId(UUID.fromString(resultSet.getString("chat_id")));
        message.setUserId(UUID.fromString(resultSet.getString("user_id")));
    }
}
