package entities;

import java.util.Date;
import java.util.UUID;

public class Message {
    public UUID id = UUID.randomUUID();
    public Date dateTime;
    public String text;
    public UUID chatId;
    public UUID userId;

    public Message() {}

    public Message(Date dateTime,
                   String text,
                   UUID chatId,
                   UUID userId) {
        this.dateTime = dateTime;
        this.text = text;
        this.chatId = chatId;
        this.userId = userId;
    }
}
