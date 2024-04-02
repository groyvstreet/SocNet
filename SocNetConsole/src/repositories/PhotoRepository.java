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
        var query = STR."INSERT INTO photos VALUES('\{photo.getId()}', '\{photo.getSource()}', '\{photo.getUserId()}');";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void updatePhoto(Photo photo) {
        var query = STR."UPDATE photos SET source='\{photo.getSource()}', user_id='\{photo.getUserId()}' WHERE id='\{photo.getId()}';";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void removePhoto(Photo photo) {
        var query = STR."DELETE FROM photos WHERE id='\{photo.getId()}'";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public ArrayList<Photo> getPhotos() {
        var query = "SELECT * FROM photos";

        return getPhotosFromQuery(query);
    }

    public Photo getPhotoById(UUID id) {
        Photo photo = null;
        var query = STR."SELECT * FROM photos WHERE id='\{id}'";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                photo = new Photo();
                photo.setId(UUID.fromString(result.getString("id")));
                photo.setSource(result.getString("source"));
                photo.setUserId(UUID.fromString(result.getString("user_id")));
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return photo;
    }

    public ArrayList<Photo> getPhotosByUserId(UUID id) {
        var query = STR."SELECT * FROM photos WHERE user_id='\{id}'";

        return getPhotosFromQuery(query);
    }

    public ArrayList<Photo> getPhotosFromQuery(String query) {
        var photos = new ArrayList<Photo>();

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                var photo = new Photo();
                photo.setId(UUID.fromString(result.getString("id")));
                photo.setSource(result.getString("source"));
                photo.setUserId(UUID.fromString(result.getString("user_id")));
                photos.add(photo);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return photos;
    }
}
