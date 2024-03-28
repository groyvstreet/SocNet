package repositories;

import entities.ChatUser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import static java.lang.System.out;

public class ChatUserRepository {
    private final Connection _connection;

    public ChatUserRepository(Connection connection) {
        _connection = connection;
    }

    public void addChatUser(ChatUser chatUser) {
        var query = STR."INSERT INTO chatusers VALUES('\{chatUser.id}', '\{chatUser.chatId}', '\{chatUser.userId}');";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void updateChatUser(ChatUser chatUser) {
        var query = STR."UPDATE chatusers SET chat_id='\{chatUser.chatId}', user_id='\{chatUser.userId}' WHERE id='\{chatUser.id}';";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void removeChatUser(ChatUser chatUser) {
        var query = STR."DELETE FROM chatusers WHERE id='\{chatUser.id}'";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public ArrayList<ChatUser> getChatUsers() {
        var chatUsers = new ArrayList<ChatUser>();
        var query = "SELECT * FROM chatusers";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                var chatUser = new ChatUser();
                chatUser.id = UUID.fromString(result.getString("id"));
                chatUser.chatId = UUID.fromString(result.getString("chat_id"));
                chatUser.userId = UUID.fromString(result.getString("user_id"));
                chatUsers.add(chatUser);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return chatUsers;
    }

    public ChatUser getChatUserById(UUID id) {
        ChatUser chatUser = null;
        var query = STR."SELECT * FROM chatusers WHERE id=\{id}";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                chatUser = new ChatUser();
                chatUser.id = UUID.fromString(result.getString("id"));
                chatUser.chatId = UUID.fromString(result.getString("chat_id"));
                chatUser.userId = UUID.fromString(result.getString("user_id"));
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return chatUser;
    }

    public ArrayList<ChatUser> getChatUsersByUserId(UUID id) {
        var chatUsers = new ArrayList<ChatUser>();
        var query = STR."SELECT * FROM chatusers WHERE user_id='\{id}'";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                var chatUser = new ChatUser();
                chatUser.id = UUID.fromString(result.getString("id"));
                chatUser.chatId = UUID.fromString(result.getString("chat_id"));
                chatUser.userId = UUID.fromString(result.getString("user_id"));
                chatUsers.add(chatUser);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return chatUsers;
    }
}
