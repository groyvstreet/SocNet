package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Comment extends Entity {
    private UUID id = UUID.randomUUID();
    private Date dateTime;
    private String text;
    private UUID postId;
    private UUID userId;
    private UUID commentId;
    private boolean isRoot;

    public Comment() {}

    public Comment(Date dateTime,
                   String text,
                   UUID postId,
                   UUID userId,
                   UUID commentId,
                   boolean isRoot) {
        this.dateTime = dateTime;
        this.text = text;
        this.postId = postId;
        this.userId = userId;
        this.commentId = commentId;
        this.isRoot = isRoot;
    }
}
