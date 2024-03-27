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
        var query = STR."INSERT INTO posts VALUES('\{post.id}', '\{post.dateTime}', '\{post.text}', '\{post.userId}');";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void updatePost(Post post) {
        var query = STR."UPDATE posts SET date_time='\{post.dateTime}', text='\{post.text}', user_id='\{post.userId}' WHERE id='\{post.id}';";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void removePost(Post post) {
        var query = STR."DELETE FROM posts WHERE id='\{post.id}'";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public ArrayList<Post> getPosts() {
        var posts = new ArrayList<Post>();
        var query = "SELECT * FROM posts";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                var post = new Post();
                post.id = UUID.fromString(result.getString("id"));
                post.dateTime = result.getDate("date_time");
                post.text = result.getString("text");
                post.userId = UUID.fromString(result.getString("user_id"));
                posts.add(post);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return posts;
    }

    public Post getPostById(UUID id) {
        Post post = null;
        var query = STR."SELECT * FROM posts WHERE id='\{id}'";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                post = new Post();
                post.id = UUID.fromString(result.getString("id"));
                post.dateTime = result.getDate("date_time");
                post.text = result.getString("text");
                post.userId = UUID.fromString(result.getString("user_id"));
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return post;
    }

    public ArrayList<Post> getPostsByUserId(UUID id) {
        var posts = new ArrayList<Post>();
        var query = STR."SELECT * FROM posts WHERE user_id='\{id}'";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                var post = new Post();
                post.id = UUID.fromString(result.getString("id"));
                post.dateTime = result.getDate("date_time");
                post.text = result.getString("text");
                post.userId = UUID.fromString(result.getString("user_id"));
                posts.add(post);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return posts;
    }
}
