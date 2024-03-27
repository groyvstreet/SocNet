package entities;

import java.util.UUID;

public class ChatUser {
    public UUID id = UUID.randomUUID();
    public UUID chatId;
    public UUID userId;

    public ChatUser() {}

    public ChatUser(UUID chatId,
                    UUID userId) {
        this.chatId = chatId;
        this.userId = userId;
    }
}
