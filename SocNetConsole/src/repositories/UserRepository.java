package repositories;

import entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import static java.lang.System.out;

public class UserRepository {
    private final Connection _connection;

    public UserRepository(Connection connection) {
        _connection = connection;
    }

    public void addUser(User user) {
        var query = STR."INSERT INTO users VALUES('\{user.getId()}', '\{user.getFirstName()}', '\{user.getLastName()}', '\{user.getBirthDate()}', '', '\{user.getRoleId()}', '\{user.getEmail()}', '\{user.getPassword()}');";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void updateUser(User user) {
        var query = STR."UPDATE users SET first_name='\{user.getFirstName()}', last_name='\{user.getLastName()}', birth_date='\{user.getBirthDate()}', image='\{user.getImage()}', role_id='\{user.getRoleId()}', email='\{user.getEmail()}', password='\{user.getPassword()}' WHERE id='\{user.getId()}';";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void removeUser(User user) {
        var query = STR."DELETE FROM users WHERE id='\{user.getId()}'";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public ArrayList<User> getUsers() {
        var users = new ArrayList<User>();
        var query = "SELECT * FROM users";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                var user = new User();
                setUserFields(user, result);
                users.add(user);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return users;
    }

    public User getUserById(UUID id) {
        var query = STR."SELECT * FROM users WHERE id=\{id}";

        return getUserBy(query);
    }

    public User getUserByEmailAndPassword(String email, String password) {
        var query = STR."SELECT * FROM users WHERE email='\{email}' and password='\{password}'";

        return getUserBy(query);
    }

    private User getUserBy(String query) {
        User user = null;

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                user = new User();
                setUserFields(user, result);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return user;
    }

    private void setUserFields(User user, ResultSet resultSet) throws SQLException {
        user.setId(UUID.fromString(resultSet.getString("id")));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setBirthDate(resultSet.getDate("birth_date"));
        user.setImage(resultSet.getString("image"));
        user.setRoleId(UUID.fromString(resultSet.getString("role_id")));
    }
}
