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
        var query = STR."INSERT INTO chatusers VALUES('\{chatUser.getId()}', '\{chatUser.getChatId()}', '\{chatUser.getUserId()}');";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void updateChatUser(ChatUser chatUser) {
        var query = STR."UPDATE chatusers SET chat_id='\{chatUser.getChatId()}', user_id='\{chatUser.getUserId()}' WHERE id='\{chatUser.getId()}';";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void removeChatUser(ChatUser chatUser) {
        var query = STR."DELETE FROM chatusers WHERE id='\{chatUser.getId()}'";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public ArrayList<ChatUser> getChatUsers() {
        var query = "SELECT * FROM chatusers";

        return getChatUsersBy(query);
    }

    public ChatUser getChatUserById(UUID id) {
        ChatUser chatUser = null;
        var query = STR."SELECT * FROM chatusers WHERE id=\{id}";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                chatUser = new ChatUser();
                chatUser.setId(UUID.fromString(result.getString("id")));
                chatUser.setChatId(UUID.fromString(result.getString("chat_id")));
                chatUser.setUserId(UUID.fromString(result.getString("user_id")));
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return chatUser;
    }

    public ArrayList<ChatUser> getChatUsersByUserId(UUID id) {
        var query = STR."SELECT * FROM chatusers WHERE user_id='\{id}'";

        return getChatUsersBy(query);
    }

    private ArrayList<ChatUser> getChatUsersBy(String query) {
        var chatUsers = new ArrayList<ChatUser>();

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                var chatUser = new ChatUser();
                chatUser.setId(UUID.fromString(result.getString("id")));
                chatUser.setChatId(UUID.fromString(result.getString("chat_id")));
                chatUser.setUserId(UUID.fromString(result.getString("user_id")));
                chatUsers.add(chatUser);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return chatUsers;
    }
}
