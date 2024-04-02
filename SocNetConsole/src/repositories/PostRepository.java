package repositories;

import entities.Post;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import static java.lang.System.out;

public class PostRepository {
    private final Connection _connection;

    public PostRepository(Connection connection) {
        _connection = connection;
    }

    public void addPost(Post post) {
        var query = STR."INSERT INTO posts VALUES('\{post.getId()}', '\{post.getDateTime()}', '\{post.getText()}', '\{post.getUserId()}');";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void updatePost(Post post) {
        var query = STR."UPDATE posts SET date_time='\{post.getDateTime()}', text='\{post.getText()}', user_id='\{post.getUserId()}' WHERE id='\{post.getId()}';";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void removePost(Post post) {
        var query = STR."DELETE FROM posts WHERE id='\{post.getId()}'";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public ArrayList<Post> getPosts() {
        var query = "SELECT * FROM posts";

        return getPostsFromQuery(query);
    }

    public Post getPostById(UUID id) {
        Post post = null;
        var query = STR."SELECT * FROM posts WHERE id='\{id}'";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                post = new Post();
                post.setId(UUID.fromString(result.getString("id")));
                post.setDateTime(result.getDate("date_time"));
                post.setText(result.getString("text"));
                post.setUserId(UUID.fromString(result.getString("user_id")));
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return post;
    }

    public ArrayList<Post> getPostsByUserId(UUID id) {
        var query = STR."SELECT * FROM posts WHERE user_id='\{id}'";

        return getPostsFromQuery(query);
    }

    private ArrayList<Post> getPostsFromQuery(String query) {
        var posts = new ArrayList<Post>();

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                var post = new Post();
                post.setId(UUID.fromString(result.getString("id")));
                post.setDateTime(result.getDate("date_time"));
                post.setText(result.getString("text"));
                post.setUserId(UUID.fromString(result.getString("user_id")));
                posts.add(post);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return posts;
    }
}
