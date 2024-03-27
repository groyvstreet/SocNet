package repositories;

import entities.User;

import java.sql.Connection;
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
        var query = STR."INSERT INTO users VALUES('\{user.id}', '\{user.firstName}', '\{user.lastName}', '\{user.birthDate}', '', '\{user.roleId}', '\{user.email}', '\{user.password}');";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void updateUser(User user) {
        var query = STR."UPDATE users SET first_name='\{user.firstName}', last_name='\{user.lastName}', birth_date='\{user.birthDate}', image='\{user.image}', role_id='\{user.roleId}', email='\{user.email}', password='\{user.password}' WHERE id='\{user.id}';";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void removeUser(User user) {
        var query = STR."DELETE FROM users WHERE id='\{user.id}'";

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
                user.id = UUID.fromString(result.getString("id"));
                user.email = result.getString("email");
                user.password = result.getString("password");
                user.firstName = result.getString("first_name");
                user.lastName = result.getString("last_name");
                user.birthDate = result.getDate("birth_date");
                user.image = result.getString("image");
                user.roleId = UUID.fromString(result.getString("role_id"));
                users.add(user);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return users;
    }

    public User getUserById(UUID id) {
        User user = null;
        var query = STR."SELECT * FROM users WHERE id=\{id}";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                user = new User();
                user.id = UUID.fromString(result.getString("id"));
                user.email = result.getString("email");
                user.password = result.getString("password");
                user.firstName = result.getString("first_name");
                user.lastName = result.getString("last_name");
                user.birthDate = result.getDate("birth_date");
                user.image = result.getString("image");
                user.roleId = UUID.fromString(result.getString("role_id"));
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return user;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        var query = STR."SELECT * FROM users WHERE email='\{email}' and password='\{password}'";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                user = new User();
                user.id = UUID.fromString(result.getString("id"));
                user.email = result.getString("email");
                user.password = result.getString("password");
                user.firstName = result.getString("first_name");
                user.lastName = result.getString("last_name");
                user.birthDate = result.getDate("birth_date");
                user.image = result.getString("image");
                user.roleId = UUID.fromString(result.getString("role_id"));
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return user;
    }
}
