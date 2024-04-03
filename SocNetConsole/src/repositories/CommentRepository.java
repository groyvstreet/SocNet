package repositories;

import entities.Comment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import static java.lang.System.out;

public class CommentRepository {
    private final Connection _connection;

    public CommentRepository(Connection connection) {
        _connection = connection;
    }

    public void addComment(Comment comment) {
        var query = STR."INSERT INTO comments VALUES('\{comment.getId()}', '\{comment.getDateTime()}', '\{comment.getText()}', '\{comment.getPostId()}', '\{comment.getUserId()}', \{comment.getCommentId() == null ? null : STR."'\{comment.getCommentId()}'"}, \{comment.isRoot()});";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void updateComment(Comment comment) {
        var query = STR."UPDATE comments SET date_time='\{comment.getDateTime()}', text='\{comment.getText()}', post_id='\{comment.getPostId()}', user_id='\{comment.getUserId()}', comment_id=\{comment.getCommentId() == null ? null : STR."'\{comment.getCommentId()}'"} WHERE id='\{comment.getId()}';";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public void removeComment(Comment comment) {
        var query = STR."DELETE FROM comments WHERE id='\{comment.getId()}'";

        try (var statement = _connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    public ArrayList<Comment> getComments() {
        var query = "SELECT * FROM comments";

        return getCommentsFromQuery(query);
    }

    public ArrayList<Comment> getCommentsByPostId(UUID postId) {
        var query = STR."SELECT * FROM comments WHERE post_id = '\{postId}'";

        return getCommentsFromQuery(query);
    }

    public Comment getCommentById(UUID id) {
        Comment comment = null;
        var query = STR."SELECT * FROM comments WHERE id='\{id}'";

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                comment = new Comment();
                setCommentFields(comment, result);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return comment;
    }

    private ArrayList<Comment> getCommentsFromQuery(String query) {
        var comments = new ArrayList<Comment>();

        try (var statement = _connection.createStatement()) {
            var result = statement.executeQuery(query);

            while (result.next()) {
                var comment = new Comment();
                setCommentFields(comment, result);
                comments.add(comment);
            }
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }

        return comments;
    }

    private void setCommentFields(Comment comment, ResultSet resultSet) throws SQLException {
        comment.setId(UUID.fromString(resultSet.getString("id")));
        comment.setDateTime(resultSet.getDate("date_time"));
        comment.setText(resultSet.getString("text"));
        comment.setPostId(UUID.fromString(resultSet.getString("post_id")));
        comment.setUserId(UUID.fromString(resultSet.getString("user_id")));
        var stringCommentId = resultSet.getString("comment_id");
        comment.setCommentId(stringCommentId == null ? null : UUID.fromString(stringCommentId));
        comment.setRoot(resultSet.getBoolean("is_root"));
    }
}
