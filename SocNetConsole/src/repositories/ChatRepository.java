package repositories;


import entities.Chat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import static java.lang.System.out;

public class ChatRepository {
    private final Connection _connection;

    public ChatRepository(Connection connection) {
        _connection = connection;
    }

    public void addChat(Chat chat) {
        var query = STR."INSERT INTO chats VALUES('\{chat.getId()}', '\{chat.getName()}');";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void updateChat(Chat chat) {
        var query = STR."UPDATE chats SET name='\{chat.getName()}' WHERE id='\{chat.getId()}';";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void removeChat(Chat chat) {
        var query = STR."DELETE FROM chats WHERE id='\{chat.getId()}'";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public ArrayList<Chat> getChats() {
        var chats = new ArrayList<Chat>();
        var query = "SELECT * FROM chats";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                var chat = new Chat();
                chat.setId(UUID.fromString(result.getString("id")));
                chat.setName(result.getString("name"));
                chats.add(chat);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return chats;
    }

    public Chat getChatById(UUID id) {
        Chat chat = null;
        var query = STR."SELECT * FROM chats WHERE id='\{id}'";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                chat = new Chat();
                chat.setId(UUID.fromString(result.getString("id")));
                chat.setName(result.getString("name"));
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return chat;
    }
}
