package screens;

import entities.Comment;
import entities.Post;
import identity.IdentityUser;
import repositories.CommentRepository;
import repositories.PostRepository;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.UUID;

import static java.lang.System.*;

public class PostsScreen {
    private PostsScreen() {}

    public static void getPostsScreen(Connection connection) throws IOException {
        var postRepository = new PostRepository(connection);
        var commentRepository = new CommentRepository(connection);

        int option;

        while (true) {
            var posts = postRepository.getPostsByUserId(IdentityUser.user.id);
            out.print("\033[H\033[2J");
            out.flush();

            for (var post : posts) {
                out.print("\u001B[32m");
                out.println("Post:");
                out.print("Id: ");
                out.print("\u001B[0m");
                out.println(post.id);
                out.print("\u001B[32m");
                out.print("Time: ");
                out.print("\u001B[0m");
                out.println(post.dateTime);
                out.print("\u001B[32m");
                out.print("Text: ");
                out.print("\u001B[0m");
                out.println(post.text);
            }

            out.println("Select option:");
            out.println("0 - Back");
            out.println("1 - Add post");
            out.println("2 - Edit post");
            out.println("3 - Remove post");
            out.println("4 - Add comment");
            out.println("5 - Edit comment");
            out.println("6 - Remove comment");
            option = in.read();

            switch (option) {
                case '0':
                    return;
                case '1':
                    out.print("Enter text: ");
                    var addedPostText = console().readLine();
                    var addedPost = new Post(new Date(), addedPostText, IdentityUser.user.id);
                    postRepository.addPost(addedPost);
                    break;
                case '2':
                    out.print("Enter id: ");
                    var editedPostId = console().readLine();
                    out.print("Enter text: ");
                    var editedPostText = console().readLine();
                    var editedPost = postRepository.getPostById(UUID.fromString(editedPostId));
                    editedPost.text = editedPostText;
                    postRepository.updatePost(editedPost);
                    break;
                case '3':
                    out.print("Enter id: ");
                    var removedPostId = console().readLine();
                    var removedPost = postRepository.getPostById(UUID.fromString(removedPostId));
                    postRepository.removePost(removedPost);
                    break;
                case '4':
                    out.print("Enter post id: ");
                    var addedCommentPostId = UUID.fromString(console().readLine());
                    out.print("Enter comment id: ");
                    var stringAddedCommentCommentId = console().readLine();
                    var addedCommentCommentId = stringAddedCommentCommentId.isEmpty() ? null : UUID.fromString(stringAddedCommentCommentId);
                    out.print("Enter text: ");
                    var addedCommentText = console().readLine();
                    var addedComment = new Comment(new Date(), addedCommentText, addedCommentPostId, IdentityUser.user.id, addedCommentCommentId);
                    commentRepository.addComment(addedComment);
                    break;
                case '5':
                    out.print("Enter id: ");
                    var editedCommentId = console().readLine();
                    out.print("Enter text: ");
                    var editedCommentText = console().readLine();
                    var editedComment = commentRepository.getCommentById(UUID.fromString(editedCommentId));
                    editedComment.text = editedCommentText;
                    commentRepository.updateComment(editedComment);
                    break;
                case '6':
                    out.print("Enter id: ");
                    var removedCommentId = console().readLine();
                    var removedComment = commentRepository.getCommentById(UUID.fromString(removedCommentId));
                    commentRepository.removeComment(removedComment);
                    break;
                default:
                    break;
            }
        }
    }
}
