package entities;

import java.util.Date;
import java.util.UUID;

public class Comment {
    public UUID id = UUID.randomUUID();
    public Date dateTime;
    public String text;
    public UUID postId;
    public UUID userId;
    public UUID commentId;

    public Comment() {}

    public Comment(Date dateTime,
                   String text,
                   UUID postId,
                   UUID userId,
                   UUID commentId) {
        this.dateTime = dateTime;
        this.text = text;
        this.postId = postId;
        this.userId = userId;
        this.commentId = commentId;
    }
}
