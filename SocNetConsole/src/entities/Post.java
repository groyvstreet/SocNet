package entities;

import java.util.Date;
import java.util.UUID;

public class Post {
    public UUID id = UUID.randomUUID();
    public Date dateTime;
    public String text;
    public UUID userId;

    public Post() {}

    public Post(Date dateTime,
                String text,
                UUID userId) {
        this.dateTime = dateTime;
        this.text = text;
        this.userId = userId;
    }
}
