package repositories;

import entities.Role;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import static java.lang.System.out;

public class RoleRepository {
    private final Connection _connection;

    public RoleRepository(Connection connection) {
        _connection = connection;
    }

    public void addRole(Role role) {
        var query = STR."INSERT INTO roles VALUES('\{role.id}', '\{role.name}');";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void updateRole(Role role) {
        var query = STR."UPDATE roles SET name='\{role.name}';";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void removeRole(Role role) {
        var query = STR."DELETE FROM roles WHERE id='\{role.id}'";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public ArrayList<Role> getRoles() {
        var roles = new ArrayList<Role>();
        var query = "SELECT * FROM roles";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                var role = new Role();
                role.id = UUID.fromString(result.getString("id"));
                role.name = result.getString("name");
                roles.add(role);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return roles;
    }

    public Role getRoleById(UUID id) {
        Role role = null;
        var query = STR."SELECT * FROM roles WHERE id=\{id}";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                role = new Role();
                role.id = UUID.fromString(result.getString("id"));
                role.name = result.getString("name");
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return role;
    }
}
