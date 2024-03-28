package screens;

import constants.Constants;
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
            var posts = postRepository.getPostsByUserId(IdentityUser.getUser().getId());
            console().printf("\033[H\033[2J");
            console().flush();

            for (var post : posts) {
                console().printf(Constants.GREEN);
                console().printf("Post:\n");
                console().printf("Id: ");
                console().printf(Constants.WHITE);
                console().printf(STR."\{post.getId()}\n");
                console().printf(Constants.GREEN);
                console().printf("Time: ");
                console().printf(Constants.WHITE);
                console().printf(STR."\{post.getDateTime()}\n");
                console().printf(Constants.GREEN);
                console().printf("Text: ");
                console().printf(Constants.WHITE);
                console().printf(STR."\{post.getText()}\n");
            }

            console().printf("Select option:\n");
            console().printf("0 - Back\n");
            console().printf("1 - Add post\n");
            console().printf("2 - Edit post\n");
            console().printf("3 - Remove post\n");
            console().printf("4 - Add comment\n");
            console().printf("5 - Edit comment\n");
            console().printf("6 - Remove comment\n");
            option = in.read();

            switch (option) {
                case '0':
                    return;
                case '1':
                    console().printf("Enter text: ");
                    var addedPostText = console().readLine();
                    var addedPost = new Post(new Date(), addedPostText, IdentityUser.getUser().getId());
                    postRepository.addPost(addedPost);
                    break;
                case '2':
                    console().printf("Enter id: ");
                    var editedPostId = console().readLine();
                    console().printf("Enter text: ");
                    var editedPostText = console().readLine();
                    var editedPost = postRepository.getPostById(UUID.fromString(editedPostId));
                    editedPost.setText(editedPostText);
                    postRepository.updatePost(editedPost);
                    break;
                case '3':
                    console().printf("Enter id: ");
                    var removedPostId = console().readLine();
                    var removedPost = postRepository.getPostById(UUID.fromString(removedPostId));
                    postRepository.removePost(removedPost);
                    break;
                case '4':
                    console().printf("Enter post id: ");
                    var addedCommentPostId = UUID.fromString(console().readLine());
                    console().printf("Enter comment id: ");
                    var stringAddedCommentCommentId = console().readLine();
                    var addedCommentCommentId = stringAddedCommentCommentId.isEmpty() ? null : UUID.fromString(stringAddedCommentCommentId);
                    console().printf("Enter text: ");
                    var addedCommentText = console().readLine();
                    var addedComment = new Comment(new Date(), addedCommentText, addedCommentPostId, IdentityUser.getUser().getId(), addedCommentCommentId);
                    commentRepository.addComment(addedComment);
                    break;
                case '5':
                    console().printf("Enter id: ");
                    var editedCommentId = console().readLine();
                    console().printf("Enter text: ");
                    var editedCommentText = console().readLine();
                    var editedComment = commentRepository.getCommentById(UUID.fromString(editedCommentId));
                    editedComment.setText(editedCommentText);
                    commentRepository.updateComment(editedComment);
                    break;
                case '6':
                    console().printf("Enter id: ");
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
