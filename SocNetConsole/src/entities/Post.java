package entities;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Post {
    private UUID id = UUID.randomUUID();
    private Date dateTime;
    private String text;
    private UUID userId;

    public Post() {}

    public Post(Date dateTime,
                String text,
                UUID userId) {
        this.dateTime = dateTime;
        this.text = text;
        this.userId = userId;
    }
}
