package entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.UUID;

@Data
public class Chat {
    private UUID id = UUID.randomUUID();
    private String name;
    private ArrayList<UUID> messageIds = new ArrayList<>();
    private ArrayList<UUID> userIds = new ArrayList<>();

    public Chat() {}

    public Chat(String name) {
        this.name = name;
    }
}
