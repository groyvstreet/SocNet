package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Message extends Entity {
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
