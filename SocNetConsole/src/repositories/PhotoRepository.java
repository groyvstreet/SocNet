package repositories;

import entities.Photo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import static java.lang.System.out;

public class PhotoRepository {
    private final Connection _connection;

    public PhotoRepository(Connection connection) {
        _connection = connection;
    }

    public void addPhoto(Photo photo) {
        var query = STR."INSERT INTO photos VALUES('\{photo.id}', '\{photo.source}', '\{photo.userId}');";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void updatePhoto(Photo photo) {
        var query = STR."UPDATE photos SET source='\{photo.source}', user_id='\{photo.userId}' WHERE id='\{photo.id}';";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void removePhoto(Photo photo) {
        var query = STR."DELETE FROM photos WHERE id='\{photo.id}'";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public ArrayList<Photo> getPhotos() {
        var photos = new ArrayList<Photo>();
        var query = "SELECT * FROM photos";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                var photo = new Photo();
                photo.id = UUID.fromString(result.getString("id"));
                photo.source = result.getString("source");
                photo.userId = UUID.fromString(result.getString("user_id"));
                photos.add(photo);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return photos;
    }

    public Photo getPhotoById(UUID id) {
        Photo photo = null;
        var query = STR."SELECT * FROM photos WHERE id='\{id}'";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                photo = new Photo();
                photo.id = UUID.fromString(result.getString("id"));
                photo.source = result.getString("source");
                photo.userId = UUID.fromString(result.getString("user_id"));
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return photo;
    }

    public ArrayList<Photo> getPhotosByUserId(UUID id) {
        var photos = new ArrayList<Photo>();
        var query = STR."SELECT * FROM photos WHERE user_id='\{id}'";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                var photo = new Photo();
                photo.id = UUID.fromString(result.getString("id"));
                photo.source = result.getString("source");
                photo.userId = UUID.fromString(result.getString("user_id"));
                photos.add(photo);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return photos;
    }
}