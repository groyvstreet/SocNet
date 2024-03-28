package entities;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Message {
    private UUID id = UUID.randomUUID();
    private Date dateTime;
    private String text;
    private UUID chatId;
    private UUID userId;

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
