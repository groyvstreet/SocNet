package entities;

import lombok.Data;

import java.util.UUID;

@Data
public class ChatUser {
    private UUID id = UUID.randomUUID();
    private UUID chatId;
    private UUID userId;

    public ChatUser() {}

    public ChatUser(UUID chatId,
                    UUID userId) {
        this.chatId = chatId;
        this.userId = userId;
    }
}
